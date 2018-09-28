package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final PageProperties pageProperties;


    @Autowired
    public RegistrationController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;

    }

    @GetMapping
    public String regPage(@ModelAttribute UserDto user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        return pageProperties.getRegistrationPagePath();
    }

}
