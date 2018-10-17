package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.FeedbackDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
