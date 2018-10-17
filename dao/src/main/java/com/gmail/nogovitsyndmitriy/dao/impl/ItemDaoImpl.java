package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

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

    public List<Feedback> getAllFeedbacksforItem(Long itemId) {
        String hql = "FROM Feedback AS F WHERE F.item.id=:itemId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("itemId", itemId);
        return query.list();
    }

    public List<Item> itemPagination(Long page, int maxResult) {
        String hqlq = "FROM Item AS I WHERE I.deleted=false";
        Query query = getCurrentSession().createQuery(hqlq);
        int startPosition = (int) ((page * maxResult) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    public List<Item> itemPaginationManage(Long page, int maxResult) {
        String hql = "FROM Item AS I";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page * maxResult) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long quantityOfItems() {
        String hql = "SELECT COUNT (*) FROM Item AS I";
        Query query = getCurrentSession().createQuery(hql);
        return (long) query.uniqueResult();
    }
}
