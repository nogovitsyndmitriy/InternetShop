package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService extends GenericService<CommentDto> {
    @Transactional
    CommentDto save(CommentDto commentDto, Long newsId);

    List<CommentDto> findCommentsByNewsId(Long id);

    Long quantityOfComments();

    List<CommentDto> commentsPangination(Long page, int maxResult);
}
