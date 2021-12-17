package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

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
    private Map<Long, GroupInfo> groups = new LinkedHashMap<>();
    private Set<BotList.BotInfo> friends = new LinkedHashSet<>();

    public void sort() {
        List<Map.Entry<Long, GroupInfo>> list = new LinkedList<>(groups.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Long, GroupInfo>>() {
            @Override
            public int compare(Map.Entry<Long, GroupInfo> o1, Map.Entry<Long, GroupInfo> o2) {
                return o2.getValue().getLevel() - o1.getValue().getLevel();
            }
        });
        groups.clear();
        for (Map.Entry<Long, GroupInfo> kv : list) {
            groups.put(kv.getKey(),kv.getValue());
        }
    }
}


