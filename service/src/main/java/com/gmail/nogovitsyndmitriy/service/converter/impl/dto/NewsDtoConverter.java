package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("newsDtoConverter")
public class NewsDtoConverter implements DTOConverter<NewsDto, News> {

    private final DTOConverter<UserDto, User> userDTOConverter;

    @Autowired
    public NewsDtoConverter(@Qualifier("userDtoConverter") DTOConverter<UserDto, User> userDTOConverter) {
        this.userDTOConverter = userDTOConverter;
    }

    @Override
    public NewsDto toDTO(News entity) {
        if (entity == null) {
            return null;
        }
        NewsDto newsDto = new NewsDto();
        newsDto.setId(entity.getId());
        newsDto.setTitle(entity.getTitle());
        newsDto.setContent(entity.getContent());
        newsDto.setCreated(entity.getCreated());
        if (entity.getUser() != null) {
            newsDto.setUser(userDTOConverter.toDTO(entity.getUser()));
        }

        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return new ArrayList<>();
    }
}
