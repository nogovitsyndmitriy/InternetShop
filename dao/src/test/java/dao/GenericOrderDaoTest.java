package dao;

import dao.impl.GenericOrderDaoImpl;
import entities.GenericOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericOrderDaoTest {
    private GenericOrderDao genericOrderDao = new GenericOrderDaoImpl(GenericOrder.class);

    @Test
    public void saveTest(){
        Session session = genericOrderDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericOrder genericOrder = new GenericOrder();
        genericOrderDao.save(genericOrder);
        transaction.commit();
    }
}
