package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.DiscountDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.hibernate.query.Query;


import java.math.BigDecimal;
import java.util.List;

public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl(Class<Discount> clazz) {
        super(clazz);
    }


    public List<Item> findByAmountOfDiscount(BigDecimal percent) {
        String hql = "SELECT D.items FROM Discount AS D WHERE D.percent=:percent";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("percent", percent);
        return query.list();
    }
}
