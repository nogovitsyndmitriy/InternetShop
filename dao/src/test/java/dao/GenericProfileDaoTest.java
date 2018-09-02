package dao;

import dao.impl.GenericProfileDaoImpl;
import entities.GenericProfile;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericProfileDaoTest {

    GenericProfileDao genericProfileDao = new GenericProfileDaoImpl(GenericProfile.class);

    @Test
    public void saveTest(){
        Session session = genericProfileDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericProfile genericProfile = new GenericProfile();
        genericProfileDao.save(genericProfile);
        transaction.commit();
    }
}
