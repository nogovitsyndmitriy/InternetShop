package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/items")
public class ItemController {

    private final static int QUANTITY_ON_PAGE = 5;
    private final PageProperties pageProperties;
    private final ItemService itemService;


    @Autowired
    public ItemController(PageProperties pageProperties, ItemService itemService) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
    }

    @GetMapping
    public String getItems(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        long quantityOfItems = itemService.quantityOfItems();
        long pagesQuantity = quantityOfPages(quantityOfItems, QUANTITY_ON_PAGE);
        List<ItemDto> items = itemService.itemPagination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        itemService.uploadFromFile(file);
        modelMap.addAttribute("file", file);
        return "redirect:/web/items/manage_items";
    }

    @PostMapping
    public String createItem(@ModelAttribute ItemDto item, ModelMap modelMap) {
        item.setDeleted(false);
        item = itemService.save(item);
        modelMap.addAttribute("item", item);
        return pageProperties.getManageItemsPagePath();
    }

    @GetMapping(value = "/create_item")
    public String createPage(ModelMap modelMap, @ModelAttribute ItemDto item) {
        modelMap.addAttribute("item", item);
        return pageProperties.getCreateItemPagePath();
    }

    @PostMapping(value = "/remove")
    public String removeItem(@RequestParam("ids") Long[] ids, ModelMap modelMap,
                             @ModelAttribute ItemDto item,
                             @RequestParam("status") String status) {
        for (Long id : ids) {
            item = itemService.get(id);
            if (status.equals("remove")) {
                item.setDeleted(true);
            } else {
                item.setDeleted(false);
            }
        }
        itemService.update(item);
        modelMap.addAttribute("item", item);
        return "redirect:/web/items/manage_items";
    }

    @GetMapping(value = "/manage_items")
    public String manageItems(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfItems = itemService.quantityOfItems();
        Long pagesQuantity = quantityOfPages(quantityOfItems, QUANTITY_ON_PAGE);
        List<ItemDto> items = itemService.itemPagination(page, QUANTITY_ON_PAGE);
        modelMap.addAttribute("pages", pagesQuantity);
        modelMap.addAttribute("items", items);
        return pageProperties.getManageItemsPagePath();
    }

    @GetMapping(value = "/{id}")
    public String getItem(@PathVariable("id") Long id, ModelMap modelMap) {
        ItemDto item = itemService.get(id);
        modelMap.addAttribute("item", item);
        return pageProperties.getCreateOrderPagePath();
    }
}
