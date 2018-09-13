package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.News;
import service.converter.DTOConverter;
import service.model.NewsDto;

import java.util.List;

public class NewsDtoConverter implements DTOConverter<NewsDto, News> {
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

        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        return null;
    }
}
