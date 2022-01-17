package io.github.kloping.springwebmirai;

import io.github.kloping.io.ReadIOUtils;
import io.github.kloping.springwebmirai.entity.Bots;
import io.github.kloping.springwebmirai.listeners.ListenerHost;
import io.github.kloping.springwebmirai.service.SysCommandSender;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.console.command.CommandExecuteResult;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.kloping.springwebmirai.service.TerminalConfig.RECEIVERS;

/**
 * @author github-kloping
 */
@Service
public class MiraiLauncherConfiguration {

    private Bots bots;

    private Environment environment;

    private ListenerHost listener;

    public MiraiLauncherConfiguration(Bots bots, Environment environment, ListenerHost listener) {
        this.bots = bots;
        this.environment = environment;
        this.listener = listener;
        new Thread(this::startLogin).start();
    }

    public static final Map<Long, Long> LOGINS = new ConcurrentHashMap<>();

    private void startLogin() {
        String env = environment.getProperty("spring.active");
        bots.getBots().forEach(e -> {
            if (!e.getEnv().equals(env)) {
                return;
            }
            BotConfiguration configuration = BotConfiguration.getDefault();
            configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.valueOf(e.getHeartbeatStrategy()));
            configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(e.getProtocol()));
            configuration.setCacheDir(new File(e.getCacheDir()));
            configuration.fileBasedDeviceInfo(e.getDeviceFile());
            Bot bot = BotFactory.INSTANCE.newBot(e.getId(), e.getPassword(), configuration);
            bot.login();
            LOGINS.put(e.getId(), System.currentTimeMillis());
            bot.getEventChannel().registerListenerHost(listener);
        });
        MiraiConsoleImplementationTerminal terminal = null;
        MiraiConsoleTerminalLoader.INSTANCE.startAsDaemon(
                terminal = new MiraiConsoleImplementationTerminal(Paths.get("./botWorkDir/"))
        );
        new Thread(() -> {
            ReadIOUtils.ReadOutputStreamImpl ros = ReadIOUtils.connectOs(System.out);
            System.setOut(new PrintStream(ros.getOs()));
            String line = null;
            while ((line = ros.readLine()) != null) {
                if (RECEIVERS.isEmpty()) {
                    continue;
                } else {
                    String finalLine = line;
                    RECEIVERS.forEach(e -> {
                        e.onMessage(finalLine, 0);
                    });
                }
            }
        }).start();
        new Thread(() -> {
            ReadIOUtils.ReadOutputStreamImpl ros = ReadIOUtils.connectOs(System.err);
            System.setErr(new PrintStream(ros.getOs()));
            String line = null;
            while ((line = ros.readLine()) != null) {
                if (RECEIVERS.isEmpty()) {
                    continue;
                } else {
                    String finalLine = line;
                    RECEIVERS.forEach(e -> {
                        e.onMessage(finalLine, -1);
                    });
                }
            }
        }).start();
    }

    public void reLogin(long id) {
        String env = environment.getProperty("spring.active");
        bots.getBots().forEach(e -> {
            if (e.getId() != id) {
                return;
            }
            if (!e.getEnv().equals(env)) {
                return;
            }
            if (Bot.getInstanceOrNull(e.getId()) != null) {
                Bot.getInstance(e.getId()).close();
            }
            BotConfiguration configuration = BotConfiguration.getDefault();
            configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(e.getProtocol()));
            configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.valueOf(e.getHeartbeatStrategy()));
            configuration.fileBasedDeviceInfo(e.getDeviceFile());
            configuration.setCacheDir(new File(e.getCacheDir()));
            Bot bot = BotFactory.INSTANCE.newBot(e.getId(), e.getPassword(), configuration);
            bot.login();
            LOGINS.put(e.getId(), System.currentTimeMillis());
            bot.getEventChannel().registerListenerHost(listener);
        });
    }

    private static final CommandSender SENDER = SysCommandSender.INSTANCE;


    public static String execute(String arg) {
        CommandExecuteResult result = null;
        result = CommandManager.INSTANCE.executeCommand(SENDER, new PlainText(arg), false);
        return "已执行";
    }

    public static void main(String[] args) {
        execute("/help");
    }
}
