package io.github.kloping.springwebmirai.controller;

import io.github.kloping.springwebmirai.entity.BotDetail;
import io.github.kloping.springwebmirai.entity.BotList;
import io.github.kloping.springwebmirai.service.IBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class BotController {
    @Autowired
    IBotService botService;


    @PostMapping("/getBots")
    @PreAuthorize("hasRole('admin')")
    public BotList m1() {
        return botService.getBotList();
    }

    @PostMapping("/detail")
    public BotDetail m2(Long id) {
        return botService.getBotDetail(id);
    }
}
