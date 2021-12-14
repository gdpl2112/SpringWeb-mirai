package io.github.kloping.springwebmirai;

import io.github.kloping.initialize.FileInitializeValue;
import io.github.kloping.springwebmirai.entity.Bots;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class MiraiLauncherConfiguration {
    private Bots bots = new Bots();

    @Value("${spring.profiles.active}")
    private String env;

    public MiraiLauncherConfiguration() {
        new Thread(this::startLogin).start();
    }

    private void startLogin() {
//        File file =
//                new File(this.getClass().getClassLoader().getResource("botsConfiguration.json").getFile());
//        bots = FileInitializeValue.getValue(file.getAbsolutePath(), bots);
//        bots.getBots().forEach(e -> {
//            if (!e.getEnv().equals(env)) return;
//            BotConfiguration configuration = BotConfiguration.getDefault();
//        });
    }
}
