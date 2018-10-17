package com.gmail.nogovitsyndmitriy.controllers.api;

import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/items")
public class ItemAPIController {

    private Random random = new Random();
    private Map<Integer, ItemDto> items = new HashMap<>();

    private final ItemService itemService;

    @Autowired
    public ItemAPIController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('API_PERMISSIONS')")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemService.get(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('API_PERMISSIONS')")
    public ItemDto createItem(@RequestBody ItemDto item) {
        return items.put(random.nextInt(), item);
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAnyAuthority('API_PERMISSIONS')")
    public ItemDto updateItem(@PathVariable("id") Long id) {
        ItemDto item = itemService.get(id);
        item.setName("New Oil");
        itemService.update(item);
        return item;
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('API_PERMISSIONS')")
    public Boolean deleteIfNotExist(@PathVariable("id") Long id) {
        return itemService.findItemInOrders(id);
    }
}
