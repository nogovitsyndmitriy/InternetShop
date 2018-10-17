package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("newsConverter")
public class NewsConverter implements Converter<News, NewsDto> {
    private final Converter<User, UserDto> userConverter;

    @Autowired
    public NewsConverter(@Qualifier("userConverter") Converter<User, UserDto> userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public News toEntity(NewsDto dto) {
        if (dto == null) {
            return null;
        }
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        if (dto.getUser() != null) {
            news.setUser(userConverter.toEntity(dto.getUser()));
        }

        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDto> list) {
        return new ArrayList<>();
    }
}
