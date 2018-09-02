package dao;

import dao.impl.GenericCommentDaoImpl;
import entities.GenericComment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.time.LocalDateTime;

public class GenericCommentDaoTest {
    private GenericCommentDao genericCommentDao = new GenericCommentDaoImpl(GenericComment.class);

    @Test
    public void saveTest(){
        Session session = genericCommentDao.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        GenericComment comment = new GenericComment("asfsa", LocalDateTime.now(),3);
        genericCommentDao.save(comment);
        transaction.commit();

    }
}
