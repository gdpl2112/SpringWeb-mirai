package io.github.kloping.springwebmirai;

import io.github.kloping.initialize.FileInitializeValue;
import io.github.kloping.springwebmirai.entity.Bots;
import io.github.kloping.springwebmirai.listeners.ListenerHost;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;

@Configuration
public class MiraiLauncherConfiguration {
    private Bots bots = new Bots();

    private Environment environment;

    @Autowired
    ListenerHost listener;

    public MiraiLauncherConfiguration(Environment environment) {
        this.environment = environment;
        new Thread(this::startLogin).start();
    }

    private void startLogin() {
        String env = environment.getProperty("spring.active");
        String name = env.equals("env") ? "botsConfiguration.json" : "test.json";
        File file =
                new File(this.getClass().getClassLoader().getResource(name).getFile());
        bots.getBots().add(
                new Bots.Bot().setId(1111).setPassword("1111")
        );
        bots = FileInitializeValue.getValue(file.getAbsolutePath(), bots);

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
    }
}
