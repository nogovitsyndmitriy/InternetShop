package dao.impl;

import dao.GenericCommentDao;
import entities.GenericComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GenericCommentDaoImpl extends GenericAbstractDao<GenericComment> implements GenericCommentDao {

    private static Logger log = LogManager.getLogger(GenericCommentDaoImpl.class);
    public GenericCommentDaoImpl(Class<GenericComment> clazz) {
        super(clazz);
    }

}
