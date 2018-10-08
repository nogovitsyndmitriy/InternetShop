package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import com.gmail.nogovitsyndmitriy.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/users")
public class UserController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final UserValidator userValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static int QUANTITY_ON_PAGE = 5;


    @Autowired
    public UserController(PageProperties pageProperties, UserService userService, UserValidator userValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.userValidator = userValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user = userService.save(user);
            modelMap.addAttribute("user", user);
            return "redirect:/web/login";
        }
    }

    @PostMapping(value = "/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        user.setId(id);
        user.getProfileDto().setUserId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
        }
        return "redirect:/web/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("ids") long[] ids) {
        for (long id : ids) {
            userService.deleteById(id);
        }
        return "redirect:/web/users";
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('VIEW_USERS')")
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") long page, ModelMap modelMap) {
        long quantityOfUsers = userService.quantityOfUsers();
        long pagesQuantity = quantityOfPages(quantityOfUsers, QUANTITY_ON_PAGE);
        List<UserDto> users = userService.usersPangination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }


}
