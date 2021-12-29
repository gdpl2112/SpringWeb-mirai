package io.github.kloping.springwebmirai.service;

import io.github.kloping.springwebmirai.entity.PluginInfo;

/**
 * @author github kloping
 * @version 1.0
 * @date 2021/12/29-10:44
 */
public interface IPluginService {
    /**
     * Returns all plugin info
     *
     * @return Returns all plugin info
     */
    PluginInfo[] getPlugins();
}

