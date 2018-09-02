package dao;

import dao.impl.GenericUserDaoImpl;
import entities.GenericUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericUserDaoTest {

    GenericUserDao genericUserDao = new GenericUserDaoImpl(GenericUser.class);

    @Test
    public void saveTest() {
        Session session = genericUserDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericUser genericUser = new GenericUser();
        genericUserDao.save(genericUser);
        transaction.commit();
    }
    @Test
    public void getTest(){
        Session session = genericUserDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericUser genericUser = genericUserDao.get((long)2);
        System.out.println(genericUser);
        transaction.commit();
    }
    @Test
    public void deleteTest(){
        Session session = genericUserDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericUser genericUser = new GenericUser();
        genericUser.setId(2);
        genericUserDao.delete(genericUser);
        transaction.commit();
    }
    @Test
    public void deleteByIdTest(){
        Session session = genericUserDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        genericUserDao.deleteById((long)4);
        transaction.commit();
    }
    @Test
    public void getAllTest(){
        Session session = genericUserDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        int size = genericUserDao.getAll().size();
        System.out.println(size);
        transaction.commit();
    }
}
