package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ProfileDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

public ProfileDaoImpl() {
        super(Profile.class);
    }
}
