package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> findCommentsByNewsId(Long id) {
        String hql = "FROM Comment AS C WHERE C.news.id=:id ORDER BY C.created DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public Long quantityOfComments() {
        String hql = "SELECT COUNT (*) FROM Comment AS C";
        Query query = getCurrentSession().createQuery(hql);
        return (long) query.uniqueResult();
    }

    @Override
    public List<Comment> commentsPangination(Long page, int maxResult) {
        String hql = "FROM Comment AS C";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page * maxResult) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }
}
