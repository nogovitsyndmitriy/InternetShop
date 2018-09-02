package dao;

import dao.impl.GenericPermissionDaoImpl;
import entities.GenericPermission;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericPermissionDaoTest {

    private GenericPermissionDao genericPermissionDao = new GenericPermissionDaoImpl(GenericPermission.class);
    @Test
    public void saveTest(){
        Session session = genericPermissionDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericPermission genericPermission = new GenericPermission();
        genericPermissionDao.save(genericPermission);
        transaction.commit();
    }
}
