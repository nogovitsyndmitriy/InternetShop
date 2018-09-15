package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.impl.DiscountDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.impl.ItemDaoImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.math.BigDecimal;


public class Testtt {
    @Test
    public void hql() {
        DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
        Session session = discountDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
//        List<Item> items = new ArrayList<>();
//        items = ((DiscountDaoImpl) discountDao).findByAmountOfDiscount();
//        items.stream().forEach(System.out::println);
        ItemDao itemDao = new ItemDaoImpl(Item.class);
        Long number = ((ItemDaoImpl) itemDao).count(BigDecimal.valueOf(200),BigDecimal.valueOf(300));
        System.out.println(number);
        transaction.commit();
    }
}
