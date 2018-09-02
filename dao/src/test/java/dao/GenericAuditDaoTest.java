package dao;

import dao.impl.GenericAuditDaoImpl;
import entities.GenericAudit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class GenericAuditDaoTest {
    private GenericAuditDao genericAuditDao = new GenericAuditDaoImpl(GenericAudit.class);

    @Test
    public void saveTest(){
        Session session = genericAuditDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericAudit genericAudit = new GenericAudit();
        genericAuditDao.save(genericAudit);
        transaction.commit();
    }
}
