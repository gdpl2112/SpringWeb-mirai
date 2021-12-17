package io.github.kloping.springwebmirai.controller;

import io.github.kloping.springwebmirai.entity.PluginInfo;
import io.github.kloping.springwebmirai.service.IPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PluginController {
    @Autowired
    IPluginService pluginService;

    @GetMapping("/plugins")
    public PluginInfo[] pluginInfo() {
        return pluginService.getPlugins();
    }
}