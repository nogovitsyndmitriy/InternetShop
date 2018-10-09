package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.DiscountDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {
    public DiscountDaoImpl() {
        super(Discount.class);
    }


    public List<Item> findByAmountOfDiscount(BigDecimal percent) {
        String hql = "SELECT D.items FROM Discount AS D WHERE D.percent=:percent";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("percent", percent);
        return query.list();
    }

    @Override
    public Discount findByName(String name) {
        String hql = "FROM Discount AS D WHERE D.name:=name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Discount) query.uniqueResult();
    }
}
