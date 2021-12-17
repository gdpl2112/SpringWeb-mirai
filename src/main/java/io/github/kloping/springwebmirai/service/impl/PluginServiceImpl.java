package io.github.kloping.springwebmirai.service.impl;

import io.github.kloping.springwebmirai.entity.PluginInfo;
import io.github.kloping.springwebmirai.service.IPluginService;
import net.mamoe.mirai.console.plugin.Plugin;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PluginServiceImpl implements IPluginService {
    @Override
    public PluginInfo[] getPlugins() {
        Set<PluginInfo> pluginInfos = new LinkedHashSet<>();
        for (Plugin plugin : PluginManager.INSTANCE.getPlugins()) {
            try {
                JvmPlugin jvmPlugin = (JvmPlugin) plugin;
                JvmPluginDescription description = jvmPlugin.getDescription();

                pluginInfos.add(new PluginInfo()
                        .setName(description.getName())
                        .setAuthor(description.getAuthor())
                        .setVersion(String.valueOf(description.getVersion()))
                        .setDesc(description.getInfo())
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pluginInfos.toArray(new PluginInfo[0]);
    }
}
