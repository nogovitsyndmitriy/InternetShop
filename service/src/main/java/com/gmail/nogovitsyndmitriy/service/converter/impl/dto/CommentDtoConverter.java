package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("commentDtoConverter")
public class CommentDtoConverter implements DTOConverter<CommentDto, Comment> {
    private final NewsDtoConverter newsDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Autowired
    public CommentDtoConverter(@Qualifier("newsDtoConverter") NewsDtoConverter newsDtoConverter, @Qualifier("userDtoConverter") UserDtoConverter userDtoConverter) {
        this.newsDtoConverter = newsDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public CommentDto toDTO(Comment entity) {
        if (entity == null) {
            return null;
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setContent(entity.getContent());
        commentDto.setCreated(entity.getCreated());
//        News
        if (entity.getNews() != null) {
            NewsDto newsDto = newsDtoConverter.toDTO(entity.getNews());
            commentDto.setNewsDto(newsDto);
        }
//        User
        if (entity.getUser() != null) {
            UserDto userDto = userDtoConverter.toDTO(entity.getUser());
            commentDto.setUserDto(userDto);
        }
        return commentDto;
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> list) {
        return null;
    }
}
