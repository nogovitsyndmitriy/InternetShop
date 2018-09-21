package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CommentConverter implements Converter<Comment, CommentDto> {
    @Override
    public Comment toEntity(CommentDto dto) {
        if (dto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        //  User
        UserConverter userConverter = new UserConverter();
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            comment.setUser(user);
        }
        //  News
        NewsConverter newsConverter = new NewsConverter();
        if (dto.getNewsDto() != null) {
            News news = newsConverter.toEntity(dto.getNewsDto());
            comment.setNews(news);
        }
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> list) {
        return null;
    }
}
