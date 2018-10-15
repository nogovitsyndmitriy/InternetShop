package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.NewsDao;
import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.NewsService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.NewsDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.NewsConverter;
import com.gmail.nogovitsyndmitriy.service.model.NewsDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final static Logger log = LogManager.getLogger(NewsServiceImpl.class);
    private final NewsDtoConverter newsDtoConverter;
    private final NewsConverter newsConverter;
    private final NewsDao newsDao;
    private final UserDao userDao;
    private final CommentDao commentDao;


    @Autowired
    public NewsServiceImpl(@Qualifier("newsDtoConverter") NewsDtoConverter newsDtoConverter,
                           @Qualifier("newsConverter") NewsConverter newsConverter,
                           NewsDao newsDao,
                           UserDao userDao, CommentDao commentDao) {
        this.newsDtoConverter = newsDtoConverter;
        this.newsConverter = newsConverter;
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public NewsDto get(Long id) {
        NewsDto newsDto = new NewsDto();
        try {
            News news = newsDao.get(id);
            newsDto = newsDtoConverter.toDTO(news);
            log.info("Getting news by Id successful!");
        } catch (Exception e) {
            log.error("Getting news by Id failed!", e);
        }
        return newsDto;
    }

    @Override
    @Transactional
    public NewsDto save(NewsDto newsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        try {
            News news = newsConverter.toEntity(newsDto);
            news.setUser(userDao.get(userPrincipal.getId()));
            news.setCreated(LocalDateTime.now());
            newsDao.save(news);
            newsDto = newsDtoConverter.toDTO(news);
            log.info("Saving news successful!");
        } catch (Exception e) {
            log.error("Saving news failed!", e);
        }
        return newsDto;
    }

    @Override
    @Transactional
    public NewsDto update(NewsDto newsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        try {
            User user = userDao.get(userPrincipal.getId());
            News news = newsConverter.toEntity(newsDto);
            news.setCreated(LocalDateTime.now());
            news.setUser(user);
            newsDao.update(news);
            newsDto = newsDtoConverter.toDTO(news);
            log.info("Update news successful!");
        } catch (Exception e) {
            log.error("Update news failed!", e);
        }
        return newsDto;
    }

    @Override
    @Transactional
    public void delete(NewsDto newsDto) {
        try {
            News news = newsConverter.toEntity(newsDto);
            newsDao.save(news);
            log.info("Delete news successful!");
        } catch (Exception e) {
            log.error("Delete news failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            News news = newsDao.get(id);
            List<Comment> comments = commentDao.findCommentsByNewsId(id);
            newsDao.delete(news);
            for (Comment comment : comments) {
                commentDao.delete(comment);
            }
            log.info("Delete news by Id successful!");
        } catch (Exception e) {
            log.error("Delete news by Id failed!", e);
        }
    }

    @Override
    @Transactional
    public List<NewsDto> getAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public List<NewsDto> newsPagination(Long page, int maxResult) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        List<News> newss;
        try {
            newss = newsDao.newsPagination(page, maxResult);
            for (News news : newss) {
                newsDtoList.add(newsDtoConverter.toDTO(news));
            }
            log.info("Successful getting news pagination!");
        } catch (Exception e) {
            log.error("News pagination failed!", e);
        }
        return newsDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public Long quantityOfNews() {
        Long quantity = 0L;
        try {
            quantity = newsDao.quantityOfNews();
            log.info("Quantity of news getting successful!");
        } catch (Exception e) {
            log.error("Failed to get news quantity!", e);
        }
        return quantity;
    }

}

