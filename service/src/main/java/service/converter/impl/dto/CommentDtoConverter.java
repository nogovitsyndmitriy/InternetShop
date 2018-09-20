package service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import service.converter.DTOConverter;
import service.model.CommentDto;
import service.model.NewsDto;
import service.model.UserDto;

import java.util.List;

public class CommentDtoConverter implements DTOConverter<CommentDto, Comment> {
    private NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
    private UserDtoConverter userDtoConverter = new UserDtoConverter();

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
