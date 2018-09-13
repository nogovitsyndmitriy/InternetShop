package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import service.converter.Converter;
import service.model.ItemDto;

import java.util.List;

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

        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemDto> list) {
        return null;
    }
}
