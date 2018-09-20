package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import service.converter.DTOConverter;
import service.model.DiscountDto;
import service.model.ItemDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiscountDtoConverter implements DTOConverter<DiscountDto, Discount> {
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();

    @Override
    public DiscountDto toDTO(Discount entity) {
        if (entity == null) {
            return null;
        }
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(entity.getId());
        discountDto.setName(entity.getName());
        discountDto.setPercent(entity.getPercent());
        discountDto.setValid(entity.getValid());
//        Add Item
        Set<ItemDto> itemDtoSet = new HashSet<>();
        for (Item items : entity.getItems()) {
            itemDtoSet.add(itemDtoConverter.toDTO(items));
        }
        discountDto.setItemDtoSet(itemDtoSet);
        return discountDto;
    }


    @Override
    public List<DiscountDto> toDtoList(List<Discount> list) {
        return null;
    }
}
