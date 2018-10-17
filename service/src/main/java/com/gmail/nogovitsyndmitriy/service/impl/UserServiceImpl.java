package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Profile;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.UserService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.UserDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.UserConverter;
import com.gmail.nogovitsyndmitriy.service.exceptions.ServiceException;
import com.gmail.nogovitsyndmitriy.service.model.PasswordDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserDtoConverter userDtoConverter;
    private final UserConverter userConverter;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(@Qualifier("userDtoConverter") UserDtoConverter userDtoConverter,
                           @Qualifier("userConverter") UserConverter userConverter,
                           UserDao userDao,
                           RoleDao roleDao,
                           BCryptPasswordEncoder encoder
    ) {
        this.userDtoConverter = userDtoConverter;
        this.userConverter = userConverter;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto get(Long id) {
        UserDto userDto;
        User user = userDao.get(id);
        if (user != null) {
            userDto = userDtoConverter.toDTO(user);
            log.info("Get user successful!");
        } else {
            throw new EntityNotFoundException("Entity with id:" + id + " not found!");
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {

        try {
            User user = userConverter.toEntity(userDto);
            Role role = roleDao.findByName("CUSTOMER_USER");
            user.setRole(role);
            user.setDisabled(false);
            Profile profile = new Profile();
            user.setProfile(profile);
            profile.setUser(user);
            userDao.save(user);
            userDto = userDtoConverter.toDTO(user);
            log.info("Saving user successful!");
        } catch (Exception e) {
            log.error("Saving user failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        try {
            User user = userConverter.toEntity(userDto);
            userDao.update(user);
            userDto = userDtoConverter.toDTO(user);
            log.info("Update user successful!");
        } catch (Exception e) {
            log.error("Update user failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return userDto;
    }

    @Override
    @Transactional
    public void delete(UserDto userDto) {
        try {
            User user = userConverter.toEntity(userDto);
            userDao.delete(user);
            log.info("Delete user successful!");
        } catch (Exception e) {
            log.error("Delete user failed!", e);
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = userDao.get(id);
        if (user != null) {
            userDao.delete(user);
            log.info("Delete user by Id successful!");
        } else {
            log.warn("Delete user by Id failed!");
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public List<UserDto> getAll() {
        List<UserDto> users;
        try {
            users = userDtoConverter.toDtoList(userDao.getAll());
            log.info("Get all users successful!");
        } catch (Exception e) {
            log.info("Get all users failed!");
            throw new ServiceException("Service Exception!");
        }
        return users;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        UserDto userDto;
        try {
            User user = userDao.findByEmail(email);
            userDto = userDtoConverter.toDTO(user);
            log.info("Find user by email successful!");
        } catch (Exception e) {
            log.error("Find user by email failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Long quantityOfUsers() {
        Long quantity;
        try {
            quantity = userDao.quantityOfUsers();
            log.info("Quantity find successful!");
        } catch (Exception e) {
            log.error("Failed to count quantity!", e);
            throw new ServiceException("Service Exception!");
        }
        return quantity;
    }

    @Override
    @Transactional
    public List<UserDto> usersPangination(Long page, int maxResult) {
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
            throw new ServiceException("Service Exception!");
        }
        return usersDto;
    }

    @Override
    @Transactional
    public UserDto changePassword(PasswordDto password, UserDto userDto) {
        if (password.getConfirmPassword().equals(password.getNewPassword())) {
            password.setCurrentPassword(encoder.encode(password.getCurrentPassword()));
            password.setNewPassword(encoder.encode(password.getNewPassword()));
            password.setConfirmPassword(encoder.encode(password.getConfirmPassword()));
            userDto.setPassword(password.getNewPassword());
            User user = userConverter.toEntity(userDto);
            user.setDisabled(false);
            userDao.update(user);
            userDto = userDtoConverter.toDTO(user);
            return userDto;
        } else {
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public UserDto changePasswordAdmin(PasswordDto password, UserDto userDto) {
        password.setNewPassword(encoder.encode(password.getNewPassword()));
        userDto.setPassword(password.getNewPassword());
        User user = userConverter.toEntity(userDto);
        userDao.update(user);
        userDto = userDtoConverter.toDTO(user);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto disableUser(Long id, Boolean isDisabled) {
        User user = userDao.get(id);
        if (isDisabled.equals(true)) {
            user.setDisabled(true);
        } else {
            user.setDisabled(false);
        }
        userDao.update(user);
        UserDto userDto = userDtoConverter.toDTO(user);
        return userDto;
    }
}
