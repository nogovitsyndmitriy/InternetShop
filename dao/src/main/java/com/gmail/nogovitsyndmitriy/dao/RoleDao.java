package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Role;

public interface RoleDao extends GenericDao<Role> {
    Role findByName(String name);
}
