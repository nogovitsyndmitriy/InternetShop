package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.DiscountService;
import com.gmail.nogovitsyndmitriy.service.RoleService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.*;
import com.gmail.nogovitsyndmitriy.controllers.validators.UserValidator;
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

import static com.gmail.nogovitsyndmitriy.controllers.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/users")
public class UserController {

    private final PageProperties pageProperties;
    private final UserService userService;
    private final DiscountService discountService;
    private final UserValidator userValidator;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static int QUANTITY_ON_PAGE = 5;


    @Autowired
    public UserController(
            PageProperties pageProperties,
            UserService userService,
            DiscountService discountService,
            UserValidator userValidator,
            RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.discountService = discountService;
        this.userValidator = userValidator;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }


    @PostMapping
    public String createUser(@ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getUsersPagePath();
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user = userService.save(user);
            modelMap.addAttribute("user", user);
            return "redirect:/web/login";
        }
    }

    @PostMapping(value = "/{id}")
    public String updateUsers(@PathVariable("id") Long id, @ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        user.setId(id);
        user.getProfileDto().setUserId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
        }
        return pageProperties.getUsersPagePath();
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            userService.deleteById(id);
        }
        return "redirect:/web/users";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('VIEW_USERS')")
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfUsers = userService.quantityOfUsers();
        Long pagesQuantity = quantityOfPages(quantityOfUsers, QUANTITY_ON_PAGE);
        List<UserDto> users = userService.usersPangination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @PostMapping(value = "/discount")
    public String setDiscount(@RequestParam("ids") Long[] ids,
                              @ModelAttribute UserDto user,
                              @RequestParam("discountName") String discountName,
                              ModelMap modelMap
    ) {
        List<DiscountDto> discounts = discountService.getAll();
        for (Long id : ids) {
            user = userService.get(id);
            user.setDiscountDto(discountService.findByName(discountName));
            userService.update(user);
        }
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("discounts", discounts);
        return "redirect:/users";
    }

    @GetMapping(value = "/roles")
    public String rolesPage(@ModelAttribute UserDto user,
                            @RequestParam(value = "page", defaultValue = "1") long page,
                            ModelMap modelMap
    ) {
        Long quantityOfUsers = userService.quantityOfUsers();
        Long pagesQuantity = quantityOfPages(quantityOfUsers, QUANTITY_ON_PAGE);
        List<UserDto> users = userService.usersPangination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("user", user);
        return pageProperties.getRolesPagePath();
    }

    @PostMapping(value = "/roles")
    public String changeRole(@RequestParam("ids") Long[] ids, ModelMap modelMap, @ModelAttribute UserDto user, @RequestParam("role") String role) {
        List<RoleDto> roles = roleService.getAll();
        for (Long id : ids) {
            user = userService.get(id);
            user.setRoleDto(roleService.findByName(role));
            userService.update(user);
        }
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
        return "redirect:/web/users/roles";
    }

    @GetMapping(value = "/current")
    public String getUser(@ModelAttribute UserDto user, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        user = userService.get(userPrincipal.getId());
        modelMap.addAttribute("user", user);
        return pageProperties.getCurrentUserPagePath();
    }

    @PostMapping(value = "/{id}/current")
    public String updateCurrentUser(@PathVariable("id") Long id, @ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        user.setId(id);
        user.getProfileDto().setUserId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUsersPagePath();
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
        }
        return pageProperties.getCurrentUserPagePath();
    }

    @GetMapping(value = "/{id}/update/password")
    public String passwordPage(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("password", new PasswordDto());
        return pageProperties.getPasswordPagePath();
    }

    @PostMapping(value = "/{id}/update/password")
    public String passwordPage(@PathVariable("id") Long id,
                               @ModelAttribute PasswordDto password,
                               ModelMap modelMap) {
        UserDto user = userService.get(id);
        userService.changePassword(password, user);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("password", new PasswordDto());
        return pageProperties.getCurrentUserPagePath();
    }
}
