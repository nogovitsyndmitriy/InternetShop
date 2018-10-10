package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orderDtoConverter")
public class OrderDtoConverter implements DTOConverter<OrderDto, Order> {
    private final ItemDtoConverter itemDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Autowired
    public OrderDtoConverter(@Qualifier("itemDtoConverter") ItemDtoConverter itemDtoConverter, @Qualifier("userDtoConverter") UserDtoConverter userDtoConverter) {
        this.itemDtoConverter = itemDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public OrderDto toDTO(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setCreated(entity.getCreated());
        orderDto.setQuantity(entity.getQuantity());
        orderDto.setStatus(entity.getStatus());
//      Item
        if (entity.getItem() != null) {
            ItemDto itemDto = itemDtoConverter.toDTO(entity.getItem());
            orderDto.setItemDto(itemDto);
        }
//       Users
        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDTO(entity.getUser());
            orderDto.setUserDto(userDto);
        }

        return orderDto;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> list) {
        return null;
    }
}
