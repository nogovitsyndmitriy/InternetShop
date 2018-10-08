package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/orders")
public class OrderController {
    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final ItemService itemService;
    private final UserService userService;
    private final static int QUANTITY_ON_PAGE = 5;

    @Autowired
    public OrderController(PageProperties pageProperties, OrderService orderService, ItemService itemService, UserService userService) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String getOrdersById(@RequestParam(value = "page", defaultValue = "1") long page, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        long quantityOfOrders = orderService.findOrdersByUserId(userPrincipal.getId()).size();
        long pagesQuantity = quantityOfPages(quantityOfOrders, QUANTITY_ON_PAGE);
        List<OrderDto> orders = orderService.ordersPagination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping(value = "/createOrder/{id}")
    public String createOrder(@PathVariable("id") long id, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        OrderDto order = new OrderDto();
        order.setUserDto(userService.get(userPrincipal.getId()));
        order.setQuantity(1);
        order.setItemDto(itemService.get(id));
        orderService.save(order);
        modelMap.addAttribute("order", order);
        return "redirect:/web/items";
    }

    @PostMapping(value = "/change_status")
    public String reviewing(@RequestParam("ids") long[] ids, ModelMap modelMap, @ModelAttribute OrderDto order, @RequestParam("status") String status) {
        for (long id : ids) {
            order = orderService.get(id);
            if (status.equals("REVIEWING")) {
                order.setStatus(Status.REVIEWING);
            } else if (status.equals("IN_PROGRESS")) {
                order.setStatus(Status.IN_PROGRESS);
            } else if (status.equals("DELIVERED")) {
                order.setStatus(Status.DELIVERED);
            }
            orderService.update(order);
        }
        modelMap.addAttribute("order", order);
        return "redirect:/web/orders";
    }

}