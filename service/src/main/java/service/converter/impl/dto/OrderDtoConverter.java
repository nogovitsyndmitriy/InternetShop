package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import service.converter.DTOConverter;
import service.model.ItemDto;
import service.model.OrderDto;

import java.util.List;

public class OrderDtoConverter implements DTOConverter<OrderDto, Order> {
    @Override
    public OrderDto toDTO(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setCreated(entity.getCreated());
        orderDto.setQuantity(entity.getQuantity());
//      Item
        ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
        if (entity.getItem() != null) {
            ItemDto itemDto = itemDtoConverter.toDTO(entity.getItem());
            orderDto.setItemDto(itemDto);
        }
        return orderDto;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> list) {
        return null;
    }
}
