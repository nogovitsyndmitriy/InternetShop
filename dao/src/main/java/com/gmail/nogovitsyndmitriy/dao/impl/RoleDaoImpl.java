package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private final static Logger log = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }
}
