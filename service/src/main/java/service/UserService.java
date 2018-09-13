package service;

import service.model.UserDto;

public interface UserService extends GenericService<UserDto> {
    UserDto findByEmail(String email);
}
