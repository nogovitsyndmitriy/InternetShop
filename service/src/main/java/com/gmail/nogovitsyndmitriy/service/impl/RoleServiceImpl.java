package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.dao.enums.Roles;
import com.gmail.nogovitsyndmitriy.dao.impl.RoleDaoImpl;
import com.gmail.nogovitsyndmitriy.service.RoleService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.RoleDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.RoleConverter;
import com.gmail.nogovitsyndmitriy.service.model.RoleDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final static Logger log = LogManager.getLogger(RoleServiceImpl.class);
    private final RoleDtoConverter roleDtoConverter;
    private final RoleConverter roleConverter;
    private RoleDao roleDao = new RoleDaoImpl();
    private Role role = new Role();

    @Autowired
    public RoleServiceImpl(@Qualifier("roleDtoConverter") RoleDtoConverter roleDtoConverter, @Qualifier("roleConverter") RoleConverter roleConverter) {
        this.roleDtoConverter = roleDtoConverter;
        this.roleConverter = roleConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public RoleDto get(long id) {
        RoleDto roleDto = new RoleDto();
        try {
            role = roleDao.get(id);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Get role successful!");
        } catch (Exception e) {
            log.error("Get role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public RoleDto save(RoleDto roleDto) {
        try {
            role = roleConverter.toEntity(roleDto);
            roleDao.save(role);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Saving role successful!");
        } catch (Exception e) {
            log.error("Saving role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public RoleDto update(RoleDto roleDto) {
        try {
            role = roleConverter.toEntity(roleDto);
            roleDao.update(role);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Update role successful!");
        } catch (Exception e) {
            log.error("Update role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(RoleDto roleDto) {
        try {
            role = roleConverter.toEntity(roleDto);
            roleDao.delete(role);
            log.info("Delete role successful!");
        } catch (Exception e) {
            log.error("Delete role failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            role = roleDao.get(id);
            roleDao.delete(role);
            log.info("Delete role by Id successful!");
        } catch (Exception e) {
            log.error("Delete role by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<RoleDto> getAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public RoleDto findByName(Roles name) {
        RoleDto roleDto = new RoleDto();
        try {
            role = roleDao.findByName(name);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Get role by name successful!");
        } catch (Exception e) {
            log.error("Get role by name failed!", e);
        }
        return roleDto;

    }
}

