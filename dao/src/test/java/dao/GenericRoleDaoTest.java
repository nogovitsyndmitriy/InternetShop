package dao;

import dao.impl.GenericRoleDaoImpl;
import entities.GenericRole;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericRoleDaoTest {
    private GenericRoleDao genericRoleDao = new GenericRoleDaoImpl(GenericRole.class);

    @Test
    public void saveTest(){
        Session session = genericRoleDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericRole genericRole = new GenericRole();
        genericRoleDao.save(genericRole);
        transaction.commit();
    }
}
