package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("itemDtoConverter")
public class ItemDtoConverter implements DTOConverter<ItemDto, Item> {
    @Override
    public ItemDto toDTO(Item entity) {
        if (entity == null) {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(entity.getId());
        itemDto.setName(entity.getName());
        itemDto.setDescription(entity.getDescription());
        itemDto.setUniqueNumber(entity.getUniqueNumber());
        itemDto.setPrice(entity.getPrice());
        itemDto.setDeleted(entity.getDeleted());

        return itemDto;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> list) {
        return null;
    }
}
