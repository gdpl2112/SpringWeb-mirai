package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author github-kloping
 */
@Data
@Accessors(chain = true)
public class BotList {
    @Data
    @Accessors(chain = true)
    public static class BotInfo {
        private String src;
        private String nickname = "未知";
        private Long loginTime = -1L;
        private Long id;
        private Integer online = 0;
    }

    private Set<BotInfo> list = new CopyOnWriteArraySet<>();
}
