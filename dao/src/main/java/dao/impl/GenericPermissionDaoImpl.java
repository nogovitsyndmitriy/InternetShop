package dao.impl;

import dao.GenericPermissionDao;
import entities.GenericPermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericPermissionDaoImpl extends GenericAbstractDao<GenericPermission> implements GenericPermissionDao {

    private Logger log = LogManager.getLogger(GenericPermissionDaoImpl.class);

    public GenericPermissionDaoImpl(Class<GenericPermission> clazz) {
        super(clazz);
    }
}
