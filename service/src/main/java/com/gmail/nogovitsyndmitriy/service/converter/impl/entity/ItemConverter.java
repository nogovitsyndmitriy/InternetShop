package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("itemConverter")
public class ItemConverter implements Converter<Item, ItemDto> {
    @Override
    public Item toEntity(ItemDto dto) {
        if (dto == null) {
            return null;
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setPrice(dto.getPrice());
        item.setDeleted(dto.getDeleted());

        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDto> list) {
        List<Item> items = new ArrayList<>();
        for (ItemDto itemDto : list) {
            items.add(toEntity(itemDto));
        }
        return items;
    }
}
