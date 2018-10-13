package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.RoleDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Role;
import com.gmail.nogovitsyndmitriy.service.RoleService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.RoleDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.RoleConverter;
import com.gmail.nogovitsyndmitriy.service.model.RoleDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final static Logger log = LogManager.getLogger(RoleServiceImpl.class);
    private final RoleDtoConverter roleDtoConverter;
    private final RoleConverter roleConverter;
    private final RoleDao roleDao;


    @Autowired
    public RoleServiceImpl(@Qualifier("roleDtoConverter") RoleDtoConverter roleDtoConverter,
                           @Qualifier("roleConverter") RoleConverter roleConverter,
                           RoleDao roleDao) {
        this.roleDtoConverter = roleDtoConverter;
        this.roleConverter = roleConverter;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto get(Long id) {
        RoleDto roleDto = new RoleDto();
        try {
            Role role = roleDao.get(id);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Get role successful!");
        } catch (Exception e) {
            log.error("Get role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional
    public RoleDto save(RoleDto roleDto) {
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.save(role);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Saving role successful!");
        } catch (Exception e) {
            log.error("Saving role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional
    public RoleDto update(RoleDto roleDto) {
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.update(role);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Update role successful!");
        } catch (Exception e) {
            log.error("Update role failed!", e);
        }
        return roleDto;
    }

    @Override
    @Transactional
    public void delete(RoleDto roleDto) {
        try {
            Role role = roleConverter.toEntity(roleDto);
            roleDao.delete(role);
            log.info("Delete role successful!");
        } catch (Exception e) {
            log.error("Delete role failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Role role = roleDao.get(id);
            roleDao.delete(role);
            log.info("Delete role by Id successful!");
        } catch (Exception e) {
            log.error("Delete role by Id failed!", e);
        }

    }

    @Override
    @Transactional
    public List<RoleDto> getAll() {
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto findByName(String name) {
        RoleDto roleDto = null;
        try {
            Role role = roleDao.findByName(name);
            roleDto = roleDtoConverter.toDTO(role);
            log.info("Get role by name successful!");
        } catch (Exception e) {
            log.error("Get role by name failed!", e);
        }
        return roleDto;

    }
}

