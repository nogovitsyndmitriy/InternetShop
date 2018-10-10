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

    private final static Logger log = LogManager.getLogger(CommentDaoImpl.class);

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


}
