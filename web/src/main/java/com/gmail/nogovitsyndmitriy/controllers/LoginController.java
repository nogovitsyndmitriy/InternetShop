package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final PageProperties pageProperties;


    @Autowired
    public LoginController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;

    }

    @GetMapping
    public String login() {
        return pageProperties.getLoginPagePath();
    }

}
