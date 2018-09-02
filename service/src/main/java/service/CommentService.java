package service;

import entities.Comment;

import java.util.List;


public interface CommentService {
    Comment get(final long entityId);

    Comment save(final Comment entity);

    void update(final Comment entity);

    void delete(final Comment entity);

    void deleteById(final long entityId);

    List<Comment> getAll();
}
