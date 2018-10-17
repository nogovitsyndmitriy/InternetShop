package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("feedbackConverter")
public class FeedbackConverter implements Converter<Feedback, FeedbackDto> {
    private final UserConverter userConverter;
    private final ItemConverter itemConverter;

    @Autowired
    public FeedbackConverter(@Qualifier("userConverter") UserConverter userConverter,
                             @Qualifier("itemConverter") ItemConverter itemConverter) {
        this.userConverter = userConverter;
        this.itemConverter = itemConverter;
    }

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
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            feedback.setUser(user);
        }
//        Item
        if (dto.getItemDto() != null) {
            Item item = itemConverter.toEntity(dto.getItemDto());
            feedback.setItem(item);
        }
        return feedback;
    }

    @Override
    public List<Feedback> toEntityList(List<FeedbackDto> list) {
        return new ArrayList<>();
    }
}
