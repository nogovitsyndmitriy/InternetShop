package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;


public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private final static Logger log = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User as U where U.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);

        return (User) query.uniqueResult();
    }
}
