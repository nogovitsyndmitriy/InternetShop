package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.BusinessCardDao;
import com.gmail.nogovitsyndmitriy.dao.entities.BusinessCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {
    private final static Logger log = LogManager.getLogger(BusinessCardDaoImpl.class);

    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }

    @Override
    public List<BusinessCard> getAllById(Long id) {
        String hql = "FROM BusinessCard AS B WHERE B.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }
}
