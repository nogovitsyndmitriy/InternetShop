package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import service.converter.DTOConverter;
import service.model.FeedbackDto;
import service.model.ItemDto;
import service.model.UserDto;

import java.util.List;

public class FeedbackDtoConverter implements DTOConverter<FeedbackDto, Feedback> {
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();

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
        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDTO(entity.getUser());
            feedbackDto.setUserDto(userDto);
        }
//        Item
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
