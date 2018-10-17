package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.DiscountDto;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("discountConverter")
public class DiscountConverter implements Converter<Discount, DiscountDto> {
    private final ItemConverter itemConverter;

    @Autowired
    public DiscountConverter(@Qualifier("itemConverter") ItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

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
        Set<Item> items = new HashSet<>();
        for (ItemDto itemsDto : dto.getItemDtoSet()) {
            items.add(itemConverter.toEntity(itemsDto));
        }
        discount.setItems(items);
        return discount;
    }

    @Override
    public List<Discount> toEntityList(List<DiscountDto> list) {
        return new ArrayList<>();
    }
}

