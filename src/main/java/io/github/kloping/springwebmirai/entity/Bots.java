package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import net.mamoe.mirai.utils.BotConfiguration;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author github-kloping
 */
@Data
@Accessors(chain = true)
public class Bots {
    @Data
    @Accessors(chain = true)
    public static class Bot {
        private String env = "env";
        private long id = -1;
        private String password = "";

        private String protocol = BotConfiguration.MiraiProtocol.IPAD.name();
        private String cacheDir = "./cache/";
        private String deviceFile = "./device.json";
        private String heartbeatStrategy = BotConfiguration.HeartbeatStrategy.STAT_HB.name();
    }

    private Set<Bot> bots = new CopyOnWriteArraySet<>();

    public void apply() {

    }
}
