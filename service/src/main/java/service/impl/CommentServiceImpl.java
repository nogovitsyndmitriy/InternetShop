package service.impl;

import dao.GenericCommentDao;
import dao.impl.GenericCommentDaoImpl;
import entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.CommentService;
import util.HibernateUtil;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static final Logger log = LogManager.getFormatterLogger(HibernateUtil.class);
    GenericCommentDao genericCommentDao = new GenericCommentDaoImpl(Comment.class);
    Comment comment = new Comment();


    @Override
    public Comment get(long entityId) {
        return null;
    }

    @Override
    public Comment save(Comment entity) {
        Session session = genericCommentDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            transaction.begin();
            genericCommentDao.save(comment);
            transaction.commit();
        } catch (Exception e){
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            log.info("Failed to save comment!");
        }
        return comment;
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }

    @Override
    public List<Comment> getAll() {
        return null;
    }
}
