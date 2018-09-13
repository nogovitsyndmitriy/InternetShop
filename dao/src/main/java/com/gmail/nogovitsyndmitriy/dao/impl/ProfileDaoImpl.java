package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    private final static Logger log = LogManager.getLogger(ProfileDaoImpl.class);

public ProfileDaoImpl(Class<Profile> clazz) {
        super(clazz);
    }
}
