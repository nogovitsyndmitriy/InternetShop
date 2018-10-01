package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.UserDto;

import java.util.List;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);

    long quantityOfUsers();

    List<UserDto> usersPangination(long page, int maxResult);
}
