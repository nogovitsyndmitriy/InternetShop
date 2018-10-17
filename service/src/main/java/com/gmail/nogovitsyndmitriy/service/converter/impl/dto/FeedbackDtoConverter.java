package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.FeedbackDto;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("feedbackDtoConverter")
public class FeedbackDtoConverter implements DTOConverter<FeedbackDto, Feedback> {
    private final UserDtoConverter userDtoConverter;
    private final ItemDtoConverter itemDtoConverter;

    @Autowired
    public FeedbackDtoConverter(@Qualifier("userDtoConverter") UserDtoConverter userDtoConverter, @Qualifier("itemDtoConverter") ItemDtoConverter itemDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.itemDtoConverter = itemDtoConverter;
    }

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
        return new ArrayList<>();
    }
}
