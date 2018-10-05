package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.dao.enums.Roles;
import com.gmail.nogovitsyndmitriy.service.model.RoleDto;

public interface RoleService extends GenericService<RoleDto> {
    RoleDto findByName(Roles name);
}
