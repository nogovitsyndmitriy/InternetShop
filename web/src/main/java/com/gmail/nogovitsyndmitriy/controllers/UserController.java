package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.controllers.validators.BusinessCardValidator;
import com.gmail.nogovitsyndmitriy.controllers.validators.UserValidator;
import com.gmail.nogovitsyndmitriy.service.BusinessCardService;
import com.gmail.nogovitsyndmitriy.service.DiscountService;
import com.gmail.nogovitsyndmitriy.service.RoleService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.*;
import com.gmail.nogovitsyndmitriy.service.utils.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/users")
public class UserController {


    private final PageProperties pageProperties;
    private final UserService userService;
    private final DiscountService discountService;
    private final UserValidator userValidator;
    private final RoleService roleService;
    private final BusinessCardService cardService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BusinessCardValidator cardValidator;


    @Autowired
    public UserController(
            PageProperties pageProperties,
            UserService userService,
            DiscountService discountService,
            UserValidator userValidator,
            RoleService roleService,
            BusinessCardService cardService, BCryptPasswordEncoder bCryptPasswordEncoder,
            BusinessCardValidator cardValidator) {
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.discountService = discountService;
        this.userValidator = userValidator;
        this.roleService = roleService;
        this.cardService = cardService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cardValidator = cardValidator;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        RoleDto role = user.getRoleDto();
        modelMap.addAttribute("role", role);
        return pageProperties.getUpdateUserPagePath();
    }


    @PostMapping
    public String createUser(@ModelAttribute UserDto user, BindingResult result, ModelMap modelMap) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("user", user);
            return pageProperties.getRegistrationPagePath();
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user = userService.save(user);
            modelMap.addAttribute("user", user);
            return pageProperties.getLoginPagePath();
        }
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
    public String updateUsers(@PathVariable("id") Long id,
                              @ModelAttribute UserDto user,
                              BindingResult result,
                              ModelMap modelMap) {
        user.setId(id);
        user.getProfileDto().setUserId(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "redirect:/web/users";
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
        }
        return "redirect:/web/users";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_USERS')")
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
        Long pagesQuantity = quantityOfPages(quantityOfUsers, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<UserDto> users = userService.usersPangination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("users", users);
        return pageProperties.getUsersPagePath();
    }

    @PostMapping(value = "/discount")
    @PreAuthorize("hasRole('SALE_USER')")
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
    @PreAuthorize("hasAuthority('CHANGE_ROLE')")
    public String rolesPage(@ModelAttribute UserDto user,
                            @RequestParam(value = "page", defaultValue = "1") long page,
                            ModelMap modelMap
    ) {
        Long quantityOfUsers = userService.quantityOfUsers();
        Long pagesQuantity = quantityOfPages(quantityOfUsers, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<UserDto> users = userService.usersPangination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<RoleDto> roles = roleService.getAll();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("user", user);
        return pageProperties.getRolesPagePath();
    }

    @PostMapping(value = "/roles")
    @PreAuthorize("hasAuthority('CHANGE_ROLE')")
    public String changeRole(@RequestParam("ids") Long[] ids,
                             ModelMap modelMap,
                             @ModelAttribute UserDto user,
                             @RequestParam("roleId") Long roleId) {
        List<RoleDto> roles = roleService.getAll();
        for (Long id : ids) {
            user = userService.get(id);
            user.setRoleDto(roleService.get(roleId));
            userService.update(user);
        }
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("roles", roles);
        return "redirect:/web/users/roles";
    }

    @GetMapping(value = "/current")
    @PreAuthorize("isAuthenticated()")
    public String getUser(@ModelAttribute UserDto user, ModelMap modelMap) {
        user = userService.get(CurrentUserUtil.getCurrentUser().getId());
        modelMap.addAttribute("user", user);
        RoleDto role = user.getRoleDto();
        modelMap.addAttribute("role", role);
        return pageProperties.getCurrentUserPagePath();
    }

    @PostMapping(value = "/{id}/current")
    @PreAuthorize("isAuthenticated()")
    public String updateCurrentUser(@PathVariable("id") Long id,
                                    @ModelAttribute UserDto user,
                                    BindingResult result,
                                    ModelMap modelMap) {
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
    @PreAuthorize("isAuthenticated()")
    public String passwordPage(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("password", new PasswordDto());
        return pageProperties.getPasswordPagePath();
    }

    @PostMapping(value = "/{id}/update/password")
    @PreAuthorize("isAuthenticated()")
    public String passwordPage(@PathVariable("id") Long id,
                               @ModelAttribute PasswordDto password,
                               ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        userService.changePassword(password, user);
        modelMap.addAttribute("password", new PasswordDto());
        return pageProperties.getCurrentUserPagePath();
    }

    @GetMapping(value = "/cards")
    @PreAuthorize("hasAnyAuthority('MANAGE_BUSINESS_CARD')")
    public String getCards(ModelMap modelMap, @ModelAttribute BusinessCardDto card) {
        List<BusinessCardDto> cards = cardService.getAllById(CurrentUserUtil.getCurrentUser().getId());
        modelMap.addAttribute("cards", cards);
        modelMap.addAttribute("card", card);
        return pageProperties.getBusinessCardPagePath();
    }

    @PostMapping(value = "/cards/create")
    @PreAuthorize("hasAnyAuthority('MANAGE_BUSINESS_CARD')")
    public String createCards(ModelMap modelMap,
                              @ModelAttribute BusinessCardDto card,
                              BindingResult result) {
        cardValidator.validate(card, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("card", card);
            return pageProperties.getBusinessCardPagePath();
        } else {
            cardService.save(card);
            modelMap.addAttribute("card", card);
            return "redirect:/web/users/cards";
        }
    }

    @GetMapping(value = "/disable")
    @PreAuthorize("hasAuthority('DISABLE_USERS')")
    public String changeDisableStatus(@ModelAttribute UserDto user,
                                      @RequestParam(value = "page", defaultValue = "1") long page,
                                      ModelMap modelMap) {
        Long quantityOfUsers = userService.quantityOfUsers();
        Long pagesQuantity = quantityOfPages(quantityOfUsers, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<UserDto> users = userService.usersPangination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("user", user);
        return pageProperties.getUsersDisablePagePath();
    }

    @PostMapping(value = "/disable")
    @PreAuthorize("hasAuthority('DISABLE_USERS')")
    public String disableUsers(@RequestParam("ids") Long[] ids,
                               ModelMap modelMap,
                               @ModelAttribute UserDto user,
                               @RequestParam("disabled") String disabled) {
        for (Long id : ids) {
            userService.disableUser(id, Boolean.valueOf(disabled));
        }
        modelMap.addAttribute("user", user);
        return "redirect:/web/users/disable";
    }

    @PostMapping(value = "/{id}/update/password/admin")
    @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
    public String passwordPageAdmin(@PathVariable("id") Long id,
                                    @ModelAttribute PasswordDto password,
                                    ModelMap modelMap) {
        UserDto user = userService.get(id);
        userService.changePasswordAdmin(password, user);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("password", new PasswordDto());
        return "redirect:/web/users";
    }

    @GetMapping(value = "/{id}/update/password/admin")
    @PreAuthorize("hasAuthority('CHANGE_PASSWORD')")
    public String passwordPageAdmin(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDto user = userService.get(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("password", new PasswordDto());
        return pageProperties.getPasswordAdminPagePath();
    }
}