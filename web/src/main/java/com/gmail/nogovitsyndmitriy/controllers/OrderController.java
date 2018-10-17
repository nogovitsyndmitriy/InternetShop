package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.utils.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/orders")
public class OrderController {
    private final PageProperties pageProperties;
    private final OrderService orderService;
    private final ItemService itemService;


    @Autowired
    public OrderController(PageProperties pageProperties, OrderService orderService, ItemService itemService) {
        this.pageProperties = pageProperties;
        this.orderService = orderService;
        this.itemService = itemService;

    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String getOrdersAllPagination(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfOrders = orderService.quantityOfOrders();
        Long pagesQuantity = quantityOfPages(quantityOfOrders, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<OrderDto> orders = orderService.ordersPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("orders", orders);
        Status[] statuses = Status.values();
        modelMap.addAttribute("statuses", statuses);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getOrdersById(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfOrders = (long) orderService.findOrdersByUserId(CurrentUserUtil.getCurrentUser().getId()).size();
        Long pagesQuantity = quantityOfPages(quantityOfOrders, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<OrderDto> orders = orderService.ordersPanginationById(page, Integer.parseInt(pageProperties.getQuantityOnPage()), CurrentUserUtil.getCurrentUser().getId());
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @PostMapping(value = "/create")
    @PreAuthorize("isAuthenticated()")
    public String createOrder(@RequestParam("item") Long id, ModelMap modelMap, @RequestParam("quantity") Integer quantity) {
        OrderDto order = new OrderDto();
        modelMap.addAttribute("order", order);
        ItemDto item = itemService.get(id);
        modelMap.addAttribute("item", item);
        orderService.save(order, id, quantity);
        modelMap.addAttribute("quantity", quantity);
        return "redirect:/web/items";
    }

    @PostMapping(value = "/status")
    @PreAuthorize("hasAuthority('CHANGE_STATUS')")
    public String changeStatus(@RequestParam("ids") Long[] ids,
                               @ModelAttribute OrderDto order,
                               @RequestParam("status") String status,
                               ModelMap modelMap
    ) {
        for (Long id : ids) {
            order = orderService.get(id);
            order.setStatus(Status.valueOf(status));
            orderService.update(order);
        }
        modelMap.addAttribute("order", order);
        return "redirect:/web/orders/admin";
    }
}