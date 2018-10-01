package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil;
import com.gmail.nogovitsyndmitriy.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final UserValidator userValidator;
    private final static int quantityOnPage = 10;

    @Autowired
    public UserController(PageProperties pageProperties, UserService userService, UserValidator userValidator) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }


    @PostMapping
    public String createUser(@ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.save(user);
            modelMap.addAttribute("user", user);
            return "redirect:/users";
        }
    }

    @PostMapping(value = "/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        user.setId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
        }
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("ids") long[] ids) {
        for (long id : ids) {
            userService.deleteById(id);
        }
        return "redirect:/users";
    }

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") long page, ModelMap modelMap) {
        long quantityOfUsers = userService.quantityOfUsers();
        long pagesQuantity = PanginationUtil.quantityOfPages(quantityOfUsers, quantityOnPage);
        List<UserDto> users = userService.usersPangination(page, quantityOnPage);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }
}
