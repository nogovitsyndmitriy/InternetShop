package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("commentConverter")
public class CommentConverter implements Converter<Comment, CommentDto> {
    private final UserConverter userConverter;
    private final NewsConverter newsConverter;

    @Autowired
    public CommentConverter(@Qualifier("userConverter") UserConverter userConverter, @Qualifier("newsConverter") NewsConverter newsConverter) {
        this.userConverter = userConverter;
        this.newsConverter = newsConverter;
    }

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
        if (dto.getUserDto() != null) {
            User user = userConverter.toEntity(dto.getUserDto());
            comment.setUser(user);
        }
        //  News
        if (dto.getNewsDto() != null) {
            News news = newsConverter.toEntity(dto.getNewsDto());
            comment.setNews(news);
        }
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> list) {
        return new ArrayList<>();
    }
}
