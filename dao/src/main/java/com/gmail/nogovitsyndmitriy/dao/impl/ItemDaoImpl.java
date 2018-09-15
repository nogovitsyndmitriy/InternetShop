package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    private final static Logger log = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }


    public List<Item> findItemInRangeOfPrice(BigDecimal above, BigDecimal below) {
        String hql = "FROM Item AS I WHERE I.price>:above And I.price<:below";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("below", below);
        query.setParameter("above", above);
        return query.list();
    }

    public List<Item> findByAmountOfDiscount(BigDecimal percent) {
        String hql = "SELECT I.discount FROM Item AS I WHERE I.percent=:percent";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("percent", percent);
        return query.list();
    }

    public long count(BigDecimal above, BigDecimal below){
        String hql = "SELECT COUNT (*) FROM Item AS I WHERE I.price>:above And I.price<:below";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("below", below);
        query.setParameter("above", above);
        return (Long) query.uniqueResult();
    }
}
