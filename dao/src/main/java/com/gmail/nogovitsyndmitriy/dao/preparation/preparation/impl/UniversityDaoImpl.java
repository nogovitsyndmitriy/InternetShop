package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl;

import com.gmail.nogovitsyndmitriy.dao.preparation.University;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.UniversityDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniversityDaoImpl extends PreparationDaoImpl<University> implements UniversityDao {

    private final static Logger log = LogManager.getLogger(UniversityDaoImpl.class);

    public UniversityDaoImpl(Class<University> clazz) {
        super(clazz);
    }
}
