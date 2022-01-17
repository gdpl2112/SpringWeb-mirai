package io.github.kloping.springwebmirai.controller;

import io.github.kloping.springwebmirai.MiraiLauncherConfiguration;
import io.github.kloping.springwebmirai.entity.PluginInfo;
import io.github.kloping.springwebmirai.service.IPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author github-kloping
 */
@RestController
public class PluginController {
    @Autowired
    IPluginService pluginService;

    @GetMapping("/plugins")
    public PluginInfo[] pluginInfo() {
        return pluginService.getPlugins();
    }

    @GetMapping("/run")
    public String r0(@RequestParam("arg") String arg) {
        return MiraiLauncherConfiguration.execute(arg);
    }
}
