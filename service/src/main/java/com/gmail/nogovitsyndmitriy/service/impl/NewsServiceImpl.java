package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.NewsDao;
import com.gmail.nogovitsyndmitriy.dao.impl.NewsDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.NewsDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.NewsConverter;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    private final static Logger log = LogManager.getLogger(NewsServiceImpl.class);
    private final NewsDtoConverter newsDtoConverter;
    private final NewsConverter newsConverter;
    private NewsDao newsDao = new NewsDaoImpl();
    private NewsDto newsDto = new NewsDto();
    private News news = new News();

    @Autowired
    public NewsServiceImpl(@Qualifier("newsDtoConverter") NewsDtoConverter newsDtoConverter, @Qualifier("newsConverter") NewsConverter newsConverter) {
        this.newsDtoConverter = newsDtoConverter;
        this.newsConverter = newsConverter;
    }

    @Override
    public NewsDto get(long id) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            news = newsDao.get(id);
            newsDto = newsDtoConverter.toDTO(news);
            transaction.commit();
            log.info("Getting news by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Getting news by Id failed!", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto save(NewsDto dto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            news = newsConverter.toEntity(dto);
            newsDao.save(news);
            newsDto = newsDtoConverter.toDTO(news);
            transaction.commit();
            log.info("Saving news successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving news failed!", e);
        }
        return newsDto;
    }

    @Override
    public NewsDto update(NewsDto dto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            news = newsConverter.toEntity(dto);
            newsDao.update(news);
            newsDto = newsDtoConverter.toDTO(news);
            transaction.commit();
            log.info("Update news successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update news failed!", e);
        }
        return newsDto;
    }

    @Override
    public void delete(NewsDto dto) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            news = newsConverter.toEntity(dto);
            newsDao.save(news);
            transaction.commit();
            log.info("Delete news successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete news failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            news = newsDao.get(id);
            newsDao.delete(news);
            transaction.commit();
            log.info("Delete news by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete news by Id failed!", e);
        }
    }

    @Override
    public List<NewsDto> getAll() {
        return null;
    }
}
