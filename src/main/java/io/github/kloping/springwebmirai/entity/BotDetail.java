package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Data
@Accessors(chain = true)
public class BotDetail {
    @Data
    @Accessors(chain = true)
    public static class GroupInfo {
        private String src;
        private Long id;
        private String nickname;
        private Integer state;
        private Integer level;
    }

    private Long id;
    private String src;
    private String nickname;
    private Long loginTime = -1L;
    private Map<Long, GroupInfo> groups = new ConcurrentHashMap<>();
    private Set<BotList.BotInfo> friends = new CopyOnWriteArraySet<>();
}


