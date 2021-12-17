package io.github.kloping.springwebmirai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UseController {
    @RequestMapping("/")
    public String showLogin() {
        return "redirect:/login.html";
    }

    @GetMapping(value = {"/manager/", "/manager/index"})
    public String m0() {
        return "redirect:/manager/index.html";
    }

    @RequestMapping("/fail")
    public String fail() {
        return "redirect:/login.html?type=err";
    }
}
