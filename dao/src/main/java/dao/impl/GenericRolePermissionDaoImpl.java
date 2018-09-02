package dao.impl;

import dao.GenericRolePermissionDao;
import entities.GenericRolePermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericRolePermissionDaoImpl extends GenericAbstractDao<GenericRolePermission> implements GenericRolePermissionDao {

    private static Logger log = LogManager.getLogger(GenericRolePermissionDaoImpl.class);

    public GenericRolePermissionDaoImpl(Class<GenericRolePermission> clazz) {
        super(clazz);
    }

}
