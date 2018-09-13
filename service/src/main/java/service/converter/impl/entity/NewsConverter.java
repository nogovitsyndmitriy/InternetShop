package service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.News;
import service.converter.Converter;
import service.model.NewsDto;

import java.util.List;

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
