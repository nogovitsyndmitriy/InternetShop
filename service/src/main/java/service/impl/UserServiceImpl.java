package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entities.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AbstractService;
import service.UserService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {
    private static volatile UserService INSTANCE = null;
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao = UserDaoImpl.getINSTANCE();
    User user = new User();

    public static UserService getINSTANCE() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public User getUserByAccount(String login) {

        try {
            startTransaction();
            user = userDao.getUserByAccount(login);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        try {
            startTransaction();
            user = userDao.get(id);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return user;
    }

    @Override
    public void update(User user) {
        try {
            startTransaction();
            userDao.update(user);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            userDao.delete(id);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return 0;
    }

    @Override
    public User save(User user) {
        try {
            startTransaction();
            User newuser = new User(user.getLogin(), user.getPassword(), user.getName(), user.getLastName(), user.getAge(), user.getEmail());
            userDao.save(newuser);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new LinkedList<>();
        try {
            startTransaction();
            list = userDao.getAll();
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return list;
    }
}
