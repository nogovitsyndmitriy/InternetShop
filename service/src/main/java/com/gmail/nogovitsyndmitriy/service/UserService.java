package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.PasswordDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;

import java.util.List;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);

    Long quantityOfUsers();

    List<UserDto> usersPangination(Long page, int maxResult);

    UserDto changePassword(PasswordDto password, UserDto user);

    UserDto disableUser(Long id, Boolean isDisabled);

    UserDto changePasswordAdmin(PasswordDto password, UserDto userDto);
}
