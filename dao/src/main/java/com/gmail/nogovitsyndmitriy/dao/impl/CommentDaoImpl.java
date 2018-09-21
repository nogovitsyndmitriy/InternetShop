package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.CommentDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    private final static Logger log = LogManager.getLogger(CommentDaoImpl.class);

    public CommentDaoImpl() {
        super(Comment.class);
    }
}
