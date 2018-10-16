package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.CommentDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.CommentConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.NewsConverter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import com.gmail.nogovitsyndmitriy.service.utils.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final static Logger log = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentConverter commentConverter;
    private final CommentDtoConverter commentDtoConverter;
    private final CommentDao commentDao;
    private final NewsConverter newsConverter;
    private final NewsService newsService;
    private final UserDao userDao;


    @Autowired
    public CommentServiceImpl(@Qualifier("commentConverter") CommentConverter commentConverter,
                              @Qualifier("commentDtoConverter") CommentDtoConverter commentDtoConverter,
                              CommentDao commentDao,
                              @Qualifier("newsConverter") NewsConverter newsConverter,
                              NewsService newsService, UserDao userDao) {
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
        this.commentDao = commentDao;
        this.newsConverter = newsConverter;
        this.newsService = newsService;
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto get(Long id) {
        CommentDto commentDto;
        Comment comment = commentDao.get(id);
        if (comment != null) {
            commentDto = commentDtoConverter.toDTO(comment);
            log.info("Getting comment by Id successful!");
        } else {
            throw new EntityNotFoundException("Item NOT Found!");
        }
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto save(CommentDto commentDto, Long newsId) {
        try {
            News news = newsConverter.toEntity(newsService.get(newsId));
            Comment comment = commentConverter.toEntity(commentDto);
            comment.setUser(userDao.findByEmail(CurrentUserUtil.getCurrentUser().getUsername()));
            comment.setNews(news);
            comment.setCreated(LocalDateTime.now());
            commentDao.save(comment);
            commentDto = commentDtoConverter.toDTO(comment);
            log.info("Saving comment successful!");
        } catch (Exception e) {
            log.error("Saving comment failed!", e);
        }
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto update(CommentDto commentDto) {
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.update(comment);
            commentDto = commentDtoConverter.toDTO(comment);
            log.info("Update comment successful!");
        } catch (Exception e) {
            log.error("Update comment failed!", e);
        }
        return commentDto;
    }

    @Override
    @Transactional
    public void delete(CommentDto commentDto) {
        try {
            Comment comment = commentConverter.toEntity(commentDto);
            commentDao.delete(comment);
            log.info("Saving comment successful!");
        } catch (Exception e) {
            log.error("Saving comment failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Comment comment = commentDao.get(id);
        if (comment != null) {
            commentDao.delete(comment);
            log.info("Delete comment successful!");
        } else {
            throw new EntityNotFoundException("Item NOT Found!");
        }
    }

    @Override
    @Transactional
    public List<CommentDto> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findCommentsByNewsId(Long id) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> comments = commentDao.findCommentsByNewsId(id);
        for (Comment comment : comments) {
            commentDtoList.add(commentDtoConverter.toDTO(comment));
        }
        return commentDtoList;
    }

    @Override
    @Transactional
    public Long quantityOfComments() {
        Long quantity = 0L;
        try {
            quantity = commentDao.quantityOfComments();
            log.info("Quantity find successful!");
        } catch (Exception e) {
            log.error("Failed to count quantity!", e);
        }
        return quantity;
    }

    @Override
    @Transactional
    public List<CommentDto> commentsPangination(Long page, int maxResult) {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments;
        try {
            comments = commentDao.commentsPangination(page, maxResult);
            for (Comment comment : comments) {
                commentsDto.add(commentDtoConverter.toDTO(comment));
            }
            log.info("Comments pangination successful!");
        } catch (Exception e) {
            log.error("Failed to get comments pangination");
        }
        return commentsDto;
    }

    @Override
    public CommentDto save(CommentDto dto) {
        throw new UnsupportedOperationException();
    }
}
