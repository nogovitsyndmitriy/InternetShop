package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.FeedbackDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FeedbackConverter implements Converter<Feedback, FeedbackDto> {
    @Override
    public Feedback toEntity(FeedbackDto dto) {
        if (dto == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setContent(dto.getContent());
        feedback.setCreated(dto.getCreated());
//        User
        UserConverter userConverter = new UserConverter();
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            feedback.setUser(user);
        }
//        Item
        ItemConverter itemConverter = new ItemConverter();
        if (dto.getItemDto() != null) {
            Item item = itemConverter.toEntity(dto.getItemDto());
            feedback.setItem(item);
        }
        return null;
    }

    @Override
    public List<Feedback> toEntityList(List<FeedbackDto> list) {
        return null;
    }
}
