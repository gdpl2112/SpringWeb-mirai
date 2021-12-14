package io.github.kloping.springwebmirai;

import io.github.kloping.springwebmirai.entity.Bots;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiraiLauncherConfiguration {
    private Bots bots;

    @Value("${spring.profiles.active}")
    private String env;

    public MiraiLauncherConfiguration() {
        new Thread(this::startLogin).start();
    }

    private void startLogin() {
    }
}
