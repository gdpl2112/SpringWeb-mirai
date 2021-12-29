package io.github.kloping.springwebmirai.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github-kloping
 */
@Data
@Accessors(chain = true)
public class PluginInfo {
    private String name;
    private String author;
    private String version;
    private String desc;
}
