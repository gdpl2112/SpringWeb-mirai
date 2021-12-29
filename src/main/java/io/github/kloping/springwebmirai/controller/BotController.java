package io.github.kloping.springwebmirai.controller;

import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;
import io.github.kloping.springwebmirai.service.IBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author github-kloping
 */
@RestController
@PreAuthorize("hasRole('admin')")
@RequestMapping("/manager")
public class BotController {
    @Autowired
    IBotService botService;

    @PostMapping("/getBots")
    public BotList m1() {
        return botService.getBotList();
    }

    @PostMapping("/detail")
    public BotDetail m2(Long id) {
        return botService.getBotDetail(id);
    }

    @PostMapping("/offline")
    public BotList m3(long id) {
        return botService.offline(id);
    }

    @PostMapping("/online")
    public BotList m4(long id) {
        return botService.online(id);
    }
}
