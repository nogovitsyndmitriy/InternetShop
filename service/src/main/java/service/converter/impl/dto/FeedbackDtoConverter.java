package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import service.converter.DTOConverter;
import service.model.FeedbackDto;
import service.model.ItemDto;
import service.model.UserDto;

import java.util.List;

public class FeedbackDtoConverter implements DTOConverter<FeedbackDto, Feedback> {
    @Override
    public FeedbackDto toDTO(Feedback entity) {
        if (entity == null) {
            return null;
        }
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setId(entity.getId());
        feedbackDto.setContent(entity.getContent());
        feedbackDto.setCreated(entity.getCreated());
//        User
        UserDtoConverter userDtoConverter = new UserDtoConverter();
        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDTO(entity.getUser());
            feedbackDto.setUserDto(userDto);
        }
//        Item
        ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
        if (entity.getItem() != null) {
            ItemDto itemDto = itemDtoConverter.toDTO(entity.getItem());
            feedbackDto.setItemDto(itemDto);
        }
        return null;
    }

    @Override
    public List<FeedbackDto> toDtoList(List<Feedback> list) {
        return null;
    }
}
