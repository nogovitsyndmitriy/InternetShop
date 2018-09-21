package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.DiscountDto;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class DiscountConverter implements Converter<Discount, DiscountDto> {
    @Override
    public Discount toEntity(DiscountDto dto) {
        if (dto == null) {
            return null;
        }
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
        discount.setPercent(dto.getPercent());
        discount.setValid(dto.getValid());
//        Add Items
        ItemConverter itemConverter = new ItemConverter();
        Set<Item> items = new HashSet<>();
        for (ItemDto itemsDto : dto.getItemDtoSet()) {
            items.add(itemConverter.toEntity(itemsDto));
        }
        discount.setItems(items);
        return discount;
    }

    @Override
    public List<Discount> toEntityList(List<DiscountDto> list) {
        return null;
    }
}

