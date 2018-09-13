package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.PermissionDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

    private final static Logger log = LogManager.getLogger(PermissionDaoImpl.class);

    public PermissionDaoImpl(Class<Permission> clazz) {
        super(clazz);
    }
}
