package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List<Comment> findCommentsByNewsId(Long id);
}
