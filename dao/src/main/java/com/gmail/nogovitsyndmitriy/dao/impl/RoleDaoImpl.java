package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.dao.enums.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    private final static Logger log = LogManager.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        String hql = "FROM Role AS R WHERE R.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }
}
