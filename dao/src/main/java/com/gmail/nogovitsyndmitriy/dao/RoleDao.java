package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.dao.enums.Roles;

public interface RoleDao extends GenericDao<Role> {
    Role findByName(Roles name);
}
