package dao;

import dao.impl.GenericItemDaoImpl;
import entities.GenericItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericItemDaoTest {
    private GenericItemDao genericItemDao = new GenericItemDaoImpl(GenericItem.class);

    @Test
    public void saveTest(){
        Session session = genericItemDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericItem genericItem = new GenericItem();
        genericItemDao.save(genericItem);
        transaction.commit();
    }
}
