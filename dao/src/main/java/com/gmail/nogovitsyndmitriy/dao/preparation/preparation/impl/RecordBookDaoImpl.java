package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl;

import com.gmail.nogovitsyndmitriy.dao.preparation.RecordBook;
import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.RecordBookDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecordBookDaoImpl extends PreparationDaoImpl<RecordBook> implements RecordBookDao {

    private final static Logger log = LogManager.getLogger(RecordBookDaoImpl.class);

    public RecordBookDaoImpl(Class<RecordBook> clazz) {
        super(clazz);
    }
}
