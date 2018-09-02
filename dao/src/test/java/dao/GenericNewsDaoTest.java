package dao;

import dao.impl.GenericNewsDaoImpl;
import entities.GenericNews;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericNewsDaoTest {

    private GenericNewsDao genericNewsDao = new GenericNewsDaoImpl(GenericNews.class);

    @Test
    public void saveTest(){
        Session session = genericNewsDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericNews genericNews = new GenericNews();
        genericNewsDao.save(genericNews);
        transaction.commit();
    }
}
