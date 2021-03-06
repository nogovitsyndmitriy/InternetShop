package com.gmail.nogovitsyndmitriy.controllers;

import com.gmail.nogovitsyndmitriy.config.PageProperties;
import com.gmail.nogovitsyndmitriy.controllers.validators.ItemValidator;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.gmail.nogovitsyndmitriy.service.utils.PanginationUtil.quantityOfPages;

@Controller
@RequestMapping("/web/items")
public class ItemController {

    private final PageProperties pageProperties;
    private final ItemService itemService;
    private final ItemValidator itemValidator;


    @Autowired
    public ItemController(PageProperties pageProperties, ItemService itemService, ItemValidator itemValidator) {
        this.pageProperties = pageProperties;
        this.itemService = itemService;
        this.itemValidator = itemValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEMS')")
    public String getItems(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        long quantityOfItems = itemService.quantityOfItems();
        long pagesQuantity = quantityOfPages(quantityOfItems, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<ItemDto> items = itemService.itemPagination(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasAuthority('UPLOAD_ITEM')")
    public String upload(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        itemService.uploadFromFile(file);
        modelMap.addAttribute("file", file);
        return "redirect:/web/items/manage";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ITEM')")
    public String createItem(@ModelAttribute ItemDto item, ModelMap modelMap, BindingResult result) {
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return "redirect:/web/items/create";
        } else {
            item = itemService.save(item);
            modelMap.addAttribute("item", item);
            return "redirect:/web/items/manage";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('CREATE_ITEM')")
    public String createPage(ModelMap modelMap, @ModelAttribute ItemDto item) {
        modelMap.addAttribute("item", item);
        return pageProperties.getCreateItemPagePath();
    }

    @PostMapping(value = "/remove")
    @PreAuthorize("hasAuthority('REMOVE_ITEM')")
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
        return "redirect:/web/items/manage";
    }

    @GetMapping(value = "/manage")
    @PreAuthorize("hasAuthority('CREATE_ITEM')")
    public String manageItems(@RequestParam(value = "page", defaultValue = "1") Long page, ModelMap modelMap) {
        Long quantityOfItems = itemService.quantityOfItems();
        Long pagesQuantity = quantityOfPages(quantityOfItems, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("pages", pagesQuantity);
        List<ItemDto> items = itemService.itemPaginationManage(page, Integer.parseInt(pageProperties.getQuantityOnPage()));
        modelMap.addAttribute("items", items);
        return pageProperties.getManageItemsPagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String getItem(@PathVariable("id") Long id, ModelMap modelMap) {
        ItemDto item = itemService.get(id);
        modelMap.addAttribute("item", item);
        return pageProperties.getCreateOrderPagePath();
    }
}