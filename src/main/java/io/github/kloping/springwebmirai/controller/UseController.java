package io.github.kloping.springwebmirai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UseController {
    @RequestMapping("/")
    public String showLogin() {
        return "/login";
    }

    @RequestMapping("/success")
    public String success() {
        return "<p>ok</p>";
    }

    @RequestMapping("/fail")
    public String fail() {
        return "redirect:/login.html?type=err";
    }
}
