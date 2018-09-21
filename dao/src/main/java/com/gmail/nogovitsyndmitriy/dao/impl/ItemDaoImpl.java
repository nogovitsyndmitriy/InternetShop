package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    private final static Logger log = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl() {
        super(Item.class);
    }


    public List<Item> findItemInRangeOfPrice(BigDecimal above, BigDecimal below) {
        String hql = "FROM Item AS I WHERE I.price>:above And I.price<:below";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("below", below);
        query.setParameter("above", above);
        return query.list();
    }

    public long count(BigDecimal above, BigDecimal below) {
        String hql = "SELECT COUNT (*) FROM Item AS I WHERE I.price>:above And I.price<:below";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("below", below);
        query.setParameter("above", above);
        return (Long) query.uniqueResult();
    }

    public List<Feedback> getAllFeedbacksforItem(long itemId) {
        String hql = "FROM Feedback AS F WHERE F.item.id=:itemId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("itemId", itemId);
        return query.list();
    }
}
