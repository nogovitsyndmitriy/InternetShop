package dao.impl;

import dao.GenericProfileDao;
import entities.GenericProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericProfileDaoImpl extends GenericAbstractDao<GenericProfile> implements GenericProfileDao {

    private static Logger log = LogManager.getLogger(GenericProfileDaoImpl.class);

    public GenericProfileDaoImpl(Class<GenericProfile> clazz) {
        super(clazz);
    }

}
