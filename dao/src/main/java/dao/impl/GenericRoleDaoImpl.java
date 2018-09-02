package dao.impl;

import dao.GenericRoleDao;
import entities.GenericRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericRoleDaoImpl extends GenericAbstractDao<GenericRole> implements GenericRoleDao {

    private static Logger log = LogManager.getLogger(GenericRoleDaoImpl.class);

    public GenericRoleDaoImpl(Class<GenericRole> clazz) {
        super(clazz);
    }

}
