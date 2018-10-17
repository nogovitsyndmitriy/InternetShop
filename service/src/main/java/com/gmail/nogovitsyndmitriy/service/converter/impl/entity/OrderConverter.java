package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("orderConverter")
public class OrderConverter implements Converter<Order, OrderDto> {
    private final ItemConverter itemConverter;
    private final UserConverter userConverter;

    @Autowired
    public OrderConverter(@Qualifier("itemConverter") ItemConverter itemConverter,
                          @Qualifier("userConverter") UserConverter userConverter) {
        this.itemConverter = itemConverter;
        this.userConverter = userConverter;
    }

    @Override
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        order.setStatus(dto.getStatus());
        //  Item
        if (dto.getItemDto() != null) {
            Item item = itemConverter.toEntity(dto.getItemDto());
            order.setItem(item);
        }
        //  User
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            order.setUser(user);
        }

        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> list) {
        return new ArrayList<>();
    }
}
