package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.NewsDao;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    private final static Logger log = LogManager.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl() {
        super(News.class);
    }


    public List<News> newsPagination(Long page, int maxResult) {
        String hql = "FROM News AS N ORDER BY N.created DESC";
        Query query= getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page * maxResult) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long quantityOfNews() {
        String hql = "SELECT COUNT (*) FROM News AS N";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

}
