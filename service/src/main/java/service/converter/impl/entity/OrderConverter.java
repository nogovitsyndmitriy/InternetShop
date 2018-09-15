package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import service.converter.Converter;
import service.model.OrderDto;

import java.util.List;

public class OrderConverter implements Converter<Order, OrderDto> {
    @Override
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setCreated(dto.getCreated());
        order.setQuantity(dto.getQuantity());
        //  Item
        ItemConverter itemConverter = new ItemConverter();
        if (dto.getItemDto() != null) {
            Item item = itemConverter.toEntity(dto.getItemDto());
            order.setItem(item);
        }
        //  User
        UserConverter userConverter = new UserConverter();
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            order.setUser(user);
        }

        return order;
    }

    @Override
    public List<Order> toEntityList(List<OrderDto> list) {
        return null;
    }
}
