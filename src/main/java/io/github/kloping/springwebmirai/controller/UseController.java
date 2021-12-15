package io.github.kloping.springwebmirai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseController {
    @RequestMapping("/")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/fail")
    public String fail() {
        return "fail";
    }
}
