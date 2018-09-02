package dao.impl;

import dao.GenericUserDao;
import entities.GenericUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericUserDaoImpl extends GenericAbstractDao<GenericUser> implements GenericUserDao {

    private static Logger log = LogManager.getLogger(GenericUserDaoImpl.class);

    public GenericUserDaoImpl(Class<GenericUser> clazz) {
        super(clazz);
    }

}
