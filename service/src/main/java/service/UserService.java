package service;

import entities.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    User getUserByAccount(String login);

    User get(Serializable id);

    void update(User user);

    int delete(Serializable id);

    User save(User user);

    List<User>  getAll();
}
