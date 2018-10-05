package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.CommentDto;

import java.util.List;

public interface CommentService extends GenericService<CommentDto> {
    List<CommentDto> findCommentsByNewsId(long id);
}
