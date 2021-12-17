package io.github.kloping.springwebmirai.controller;

import io.github.kloping.springwebmirai.entity.BotList;
import io.github.kloping.springwebmirai.service.IBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseController {
    @Autowired
    IBotService botService;

    @GetMapping("/getBots")
    public BotList m1() {
        return botService.getBotList();
    }
}
