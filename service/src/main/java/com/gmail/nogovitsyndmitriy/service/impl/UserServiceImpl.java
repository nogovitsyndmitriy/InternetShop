package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.UserDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.UserConverter;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserDtoConverter userDtoConverter;
    private final UserConverter userConverter;
    private final UserDao userDao;
    private User user = new User();

    @Autowired
    public UserServiceImpl(@Qualifier("userDtoConverter") UserDtoConverter userDtoConverter, @Qualifier("userConverter") UserConverter userConverter, UserDao userDao) {
        this.userDtoConverter = userDtoConverter;
        this.userConverter = userConverter;
        this.userDao = userDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDto get(long id) {
        UserDto userDto = new UserDto();
        try {
            user = userDao.get(id);
            userDto = userDtoConverter.toDTO(user);
            log.info("Get user successful!");
        } catch (Exception e) {
            log.error("Get user failed!", e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDto save(UserDto userDto) {
        try {
            user = userConverter.toEntity(userDto);
            userDao.save(user);
            userDto = userDtoConverter.toDTO(user);
            log.info("Saving user successful!");
        } catch (Exception e) {
            log.error("Saving user failed!", e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDto update(UserDto userDto) {
        try {
            user = userConverter.toEntity(userDto);
            userDao.update(user);
            userDto = userDtoConverter.toDTO(user);
            log.info("Update user successful!");
        } catch (Exception e) {
            log.error("Update user failed!", e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(UserDto userDto) {
        try {
            user = userConverter.toEntity(userDto);
            userDao.delete(user);
            log.info("Delete user successful!");
        } catch (Exception e) {
            log.error("Delete user failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            user = userDao.get(id);
            userDao.delete(user);
            log.info("Delete user by Id successful!");
        } catch (Exception e) {
            log.error("Delete user by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        try {
            users = userDtoConverter.toDtoList(userDao.getAll());
            log.info("Get all users successful!");
        } catch (Exception e) {
            log.info("Get all users failed!");
        }
        return users;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public UserDto findByEmail(String email) {
        UserDto userDto = new UserDto();
        try {
            user = userDao.findByEmail(email);
            userDto = userDtoConverter.toDTO(user);
            log.info("Find user by email successful!");
        } catch (Exception e) {
            log.error("Find user by email failed!", e);
        }
        return userDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public long quantityOfUsers() {
        long quantity = 0;
        try {
            quantity = userDao.quantityOfUsers();
            log.info("Quantity find successful!");
        } catch (Exception e) {
            log.error("Failed to count quantity!", e);
        }
        return quantity;
    }

    @Override
    public List<UserDto> usersPangination(long page, int maxResult) {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users;
        try {
            users = userDao.usersPangination(page, maxResult);
            for (User user : users) {
                usersDto.add(userDtoConverter.toDTO(user));
            }
            log.info("User pangination successful!");
        } catch (Exception e) {
            log.error("Failed to get users pangination");
        }
        return usersDto;
    }
}
