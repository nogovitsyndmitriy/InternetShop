package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.impl.CommentDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.CommentService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.CommentDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.CommentConverter;
import com.gmail.nogovitsyndmitriy.service.model.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final static Logger log = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentConverter commentConverter;
    private final CommentDtoConverter commentDtoConverter;
    private CommentDao commentDao = new CommentDaoImpl();
    private CommentDto commentDto = new CommentDto();
    private Comment comment = new Comment();

    @Autowired
    public CommentServiceImpl(@Qualifier("commentConverter") CommentConverter commentConverter, @Qualifier("commentDtoConverter") CommentDtoConverter commentDtoConverter) {
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
    public CommentDto get(long id) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            comment = commentDao.get(id);
            commentDto = commentDtoConverter.toDTO(comment);
            transaction.commit();
            log.info("Getting comment by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Getting comment by Id failed!", e);
        }
        return commentDto;
    }

    @Override
    public CommentDto save(CommentDto dto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            comment = commentConverter.toEntity(dto);
            commentDao.save(comment);
            commentDto = commentDtoConverter.toDTO(comment);
            transaction.commit();
            log.info("Saving comment successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving comment failed!", e);
        }
        return commentDto;
    }

    @Override
    public CommentDto update(CommentDto dto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            comment = commentConverter.toEntity(dto);
            commentDao.update(comment);
            commentDto = commentDtoConverter.toDTO(comment);
            transaction.commit();
            log.info("Update comment successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update comment failed!", e);
        }
        return commentDto;
    }

    @Override
    public void delete(CommentDto dto) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            comment = commentConverter.toEntity(dto);
            commentDao.delete(comment);
            transaction.commit();
            log.info("Saving comment successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving comment failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = commentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            comment = commentDao.get(id);
            commentDao.delete(comment);
            transaction.commit();
            log.info("Delete comment successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete comment failed!", e);
        }
    }

    @Override
    public List<CommentDto> getAll() {
        return null;
    }
}
