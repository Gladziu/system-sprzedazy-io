package com.example.SWP.controller;

import com.example.SWP.enums.Role;
import com.example.SWP.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home-page")
    public String homePage() {
        return "home-page";
    }

    @PostMapping("/home-page")
    public String homePage(Role role) {
        String pageView = userService.loginPerRole(role);
        return "redirect:/" + pageView;
    }
}
