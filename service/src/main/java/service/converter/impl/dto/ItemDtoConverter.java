package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import service.converter.DTOConverter;
import service.model.ItemDto;

import java.util.List;

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

        return itemDto;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> list) {
        return null;
    }
}
