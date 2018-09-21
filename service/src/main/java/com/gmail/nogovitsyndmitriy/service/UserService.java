package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.UserDto;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);
}
