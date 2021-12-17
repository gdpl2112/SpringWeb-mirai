package io.github.kloping.springwebmirai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:/index.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/manager/", "/manager", "/manager/index"})
    public String m0() {
        return "redirect:/manager/index.html";
    }

    @RequestMapping("/fail")
    public String fail() {
        return "redirect:/login.html?type=err";
    }

    @GetMapping("/favicon.ico")
    public String favicon() {
        return "redirect:https://q1.qlogo.cn/g?b=qq&nk=3474006766&s=640";
    }
}
