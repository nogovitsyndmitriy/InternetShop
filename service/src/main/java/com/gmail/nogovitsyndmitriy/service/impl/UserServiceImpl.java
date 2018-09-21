package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.impl.UserDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.UserDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.UserConverter;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LogManager.getLogger(UserServiceImpl.class);
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private UserConverter userConverter = new UserConverter();
    private UserDao userDao = new UserDaoImpl();
    private User user = new User();

    @Override
    public UserDto get(long id) {
        Session session = userDao.getCurrentSession();
        UserDto userDto = new UserDto();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userDao.get(id);
            userDto = userDtoConverter.toDTO(user);
            transaction.commit();
            log.info("Get user successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get user failed!", e);
        }
        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userConverter.toEntity(userDto);
            userDao.save(user);
            userDto = userDtoConverter.toDTO(user);
            transaction.commit();
            log.info("Saving user successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving user failed!", e);
        }
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userConverter.toEntity(userDto);
            userDao.update(user);
            userDto = userDtoConverter.toDTO(user);
            transaction.commit();
            log.info("Update user successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update user failed!", e);
        }
        return userDto;
    }

    @Override
    public void delete(UserDto userDto) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userConverter.toEntity(userDto);
            userDao.delete(user);
            transaction.commit();
            log.info("Delete user successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete user failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userDao.get(id);
            userDao.delete(user);
            transaction.commit();
            log.info("Delete user by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete user by Id failed!", e);
        }
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        Session session = userDao.getCurrentSession();
        UserDto userDto = new UserDto();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            user = userDao.findByEmail(email);
            userDto = userDtoConverter.toDTO(user);
            transaction.commit();
            log.info("Find user by email successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Find user by email failed!", e);
        }
        return userDto;
    }
}