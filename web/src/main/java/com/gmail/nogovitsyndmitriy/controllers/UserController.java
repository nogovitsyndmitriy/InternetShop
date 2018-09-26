package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PageProperties pageProperties;
    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(ModelMap modelMap){
        List<UserDto> users = userService.getAll();
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

}
