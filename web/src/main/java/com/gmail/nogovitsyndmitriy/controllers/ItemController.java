package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final static int QUANTITY_ON_PAGE = 5;

    @Autowired
    public ItemController(PageProperties pageProperties, ItemService itemService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
    }

    @GetMapping
    public String getItems(@RequestParam(value = "page", defaultValue = "1") long page, ModelMap modelMap) {
        long quantityOfItems = itemService.quantityOfItems();
        long pagesQuantity = quantityOfPages(quantityOfItems, QUANTITY_ON_PAGE);
        List<ItemDto> items = itemService.itemPagination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }
}
