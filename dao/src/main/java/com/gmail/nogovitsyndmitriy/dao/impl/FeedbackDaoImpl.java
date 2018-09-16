package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.FeedbackDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;

public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {
    public FeedbackDaoImpl(Class<Feedback> clazz) {
        super(clazz);
    }
}
