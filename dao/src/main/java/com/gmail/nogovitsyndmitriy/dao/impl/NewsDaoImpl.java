package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.NewsDao;
import com.gmail.nogovitsyndmitriy.dao.entities.News;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {

    private final static Logger log = LogManager.getLogger(NewsDaoImpl.class);

    public NewsDaoImpl() {
        super(News.class);
    }

}
