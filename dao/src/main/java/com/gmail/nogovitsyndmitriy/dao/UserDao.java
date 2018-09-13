package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
}
