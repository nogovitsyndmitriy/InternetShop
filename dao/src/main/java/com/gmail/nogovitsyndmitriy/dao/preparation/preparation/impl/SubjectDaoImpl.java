package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl;

import com.gmail.nogovitsyndmitriy.dao.preparation.Subject;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.SubjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubjectDaoImpl extends PreparationDaoImpl<Subject> implements SubjectDao {

    private final static Logger log = LogManager.getLogger(SubjectDaoImpl.class);

    public SubjectDaoImpl(Class<Subject> clazz) {
        super(clazz);
    }



}
