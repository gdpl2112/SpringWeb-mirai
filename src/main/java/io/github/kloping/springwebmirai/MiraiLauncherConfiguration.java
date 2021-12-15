package io.github.kloping.springwebmirai;

import io.github.kloping.springwebmirai.entity.Bots;
import io.github.kloping.springwebmirai.listeners.ListenerHost;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.console.terminal.MiraiConsoleImplementationTerminal;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

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

    private void startLogin() {
        String env = environment.getProperty("spring.active");
        bots.getBots().forEach(e -> {
            if (!e.getEnv().equals(env)) return;
            BotConfiguration configuration = BotConfiguration.getDefault();
            configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.valueOf(e.getHeartbeatStrategy()));
            configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(e.getProtocol()));
            configuration.setCacheDir(new File(e.getCacheDir()));
            configuration.fileBasedDeviceInfo(e.getDeviceFile());
            Bot bot = BotFactory.INSTANCE.newBot(e.getId(), e.getPassword(), configuration);
            bot.login();
            bot.getEventChannel().registerListenerHost(listener);
        });
        MiraiConsoleTerminalLoader.INSTANCE.startAsDaemon(
                new MiraiConsoleImplementationTerminal(Paths.get("./botWorkDir/"))
        );
    }
}
