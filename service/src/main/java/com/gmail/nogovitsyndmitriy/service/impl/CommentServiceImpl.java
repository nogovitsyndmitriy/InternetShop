package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.CommentDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.CommentConverter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final static Logger log = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentConverter commentConverter;
    private final CommentDtoConverter commentDtoConverter;
    private final CommentDao commentDao;


    @Autowired
    public CommentServiceImpl(@Qualifier("commentConverter") CommentConverter commentConverter, @Qualifier("commentDtoConverter") CommentDtoConverter commentDtoConverter, CommentDao commentDao) {
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
        this.commentDao = commentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto get(Long id) {
        CommentDto commentDto = new CommentDto();
        try {
            Comment comment = commentDao.get(id);
            commentDto = commentDtoConverter.toDTO(comment);
            log.info("Getting comment by Id successful!");
        } catch (Exception e) {
            log.error("Getting comment by Id failed!", e);
        }
        return commentDto;
    }

    @Override
    @Transactional
    public CommentDto save(CommentDto commentDto) {
        try {

            Comment comment = commentConverter.toEntity(commentDto);
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
        try {
            Comment comment = commentDao.get(id);
            commentDao.delete(comment);
            log.info("Delete comment successful!");
        } catch (Exception e) {
            log.error("Delete comment failed!", e);
        }
    }

    @Override
    @Transactional
    public List<CommentDto> getAll() {
        return new LinkedList<>();
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
}
