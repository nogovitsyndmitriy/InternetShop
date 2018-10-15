package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.controllers.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/orders")
public class OrderController {
    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final ItemService itemService;
    private final UserService userService;


    @Autowired
    public OrderController(PageProperties pageProperties, OrderService orderService, ItemService itemService, UserService userService) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getOrdersAllPagination(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfOrders = orderService.quantityOfOrders();
        Long pagesQuantity = quantityOfPages(quantityOfOrders, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<OrderDto> orders = orderService.ordersPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping
    public String getOrdersById(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long quantityOfOrders = (long) orderService.findOrdersByUserId(userPrincipal.getId()).size();
        Long pagesQuantity = quantityOfPages(quantityOfOrders, Integer.parseInt(pageProperties.getQuantityOnPage()));
        List<OrderDto> orders = orderService.ordersPanginationById(page, Integer.parseInt(pageProperties.getQuantityOnPage()), userPrincipal.getId());
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping(value = "/create")
    public String createOrder(@RequestParam("item") Long id, ModelMap modelMap, @RequestParam("quantity") Integer quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        OrderDto order = new OrderDto();
        ItemDto item = itemService.get(id);
        order.setUserDto(userService.get(userPrincipal.getId()));
        order.setQuantity(quantity);
        order.setItemDto(item);
        orderService.save(order);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("quantity", quantity);
        modelMap.addAttribute("order", order);
        return "redirect:/web/items";
    }

    @PostMapping(value = "/status")
    public String changeStatus(@RequestParam("ids") Long[] ids,
                               @ModelAttribute OrderDto order,
                               @RequestParam("status") String status,
                               ModelMap modelMap
    ) {
        for (Long id : ids) {
            order = orderService.get(id);
            switch (status) {
                case "REVIEWING":
                    order.setStatus(Status.REVIEWING);
                    break;
                case "IN_PROGRESS":
                    order.setStatus(Status.IN_PROGRESS);
                    break;
                case "DELIVERED":
                    order.setStatus(Status.DELIVERED);
                    break;
            }
            orderService.update(order);
        }
        modelMap.addAttribute("order", order);
        return "redirect:/web/orders/admin";
    }

}
