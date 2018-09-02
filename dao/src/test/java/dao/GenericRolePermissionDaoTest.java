package dao;

import dao.impl.GenericRolePermissionDaoImpl;
import entities.GenericRolePermission;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericRolePermissionDaoTest {

    private GenericRolePermissionDao rolePermissionDao = new GenericRolePermissionDaoImpl(GenericRolePermission.class);
    @Test
    public void saveTest(){
        Session session = rolePermissionDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericRolePermission genericRolePermission = new GenericRolePermission();
        rolePermissionDao.save(genericRolePermission);
        transaction.commit();
    }
}
