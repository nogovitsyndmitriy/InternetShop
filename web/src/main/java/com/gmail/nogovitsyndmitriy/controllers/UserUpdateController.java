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
@RequestMapping("/user_update")
public class UserUpdateController {
    private final PageProperties pageProperties;

    @Autowired
    public UserUpdateController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @GetMapping
    public String updateUser(@ModelAttribute UserDto user, ModelMap modelMap) {

        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }
}
