package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import service.converter.DTOConverter;
import service.model.ItemDto;
import service.model.OrderDto;
import service.model.UserDto;

import java.util.List;

public class OrderDtoConverter implements DTOConverter<OrderDto, Order> {
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
    private UserDtoConverter userDtoConverter = new UserDtoConverter();

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
