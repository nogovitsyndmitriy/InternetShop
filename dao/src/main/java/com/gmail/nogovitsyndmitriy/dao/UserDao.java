package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);

    long quantityOfUsers();

    List<User> usersPangination(long page, int maxResult);
}
