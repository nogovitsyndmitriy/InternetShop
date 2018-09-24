package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("newsConverter")
public class NewsConverter implements Converter<News, NewsDto> {
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

        return news;
    }

    @Override
    public List<News> toEntityList(List<NewsDto> list) {
        return null;
    }
}
