package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private final static Logger log = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "FROM User AS U WHERE U.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);

        return (User) query.uniqueResult();
    }

    @Override
    public Long quantityOfUsers() {
        String hql = "SELECT COUNT (*) FROM User AS U";
        Query query = getCurrentSession().createQuery(hql);
        return (long) query.uniqueResult();
    }

    @Override
    public List<User> usersPangination(Long page, int maxResult) {
        String hql = "FROM User AS U";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((page * maxResult) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }
}
