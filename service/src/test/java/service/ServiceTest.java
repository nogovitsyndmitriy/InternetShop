package service;

import dao.GenericCommentDao;
import dao.impl.GenericCommentDaoImpl;
import entities.Comment;
import org.junit.Test;
import service.impl.CommentServiceImpl;

import java.time.LocalDateTime;

public class ServiceTest
{
    CommentService commentService = new CommentServiceImpl();
    @Test
    public void shouldAnswerWithTrue()
    {
        Comment comment = new Comment("aqqqsda",LocalDateTime.now(),4);
        commentService.save(comment);
    }
}
