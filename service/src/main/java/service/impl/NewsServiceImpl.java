package service.impl;

import com.gmail.nogovitsyndmitriy.dao.NewsDao;
import com.gmail.nogovitsyndmitriy.dao.impl.NewsDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.NewsService;
import service.converter.impl.dto.NewsDtoConverter;
import service.converter.impl.entity.NewsConverter;
import service.model.NewsDto;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final static Logger log = LogManager.getLogger(NewsServiceImpl.class);
    private NewsDtoConverter newsDtoConverter = new NewsDtoConverter();
    private NewsConverter newsConverter = new NewsConverter();
    private NewsDao newsDao = new NewsDaoImpl(News.class);
    private NewsDto newsDto = new NewsDto();
    private News news = new News();

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
