package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.impl.DiscountDaoImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class Testtt {
    @Test
    public void hql() {
        DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
        Session session = discountDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();




        transaction.commit();
    }
}
