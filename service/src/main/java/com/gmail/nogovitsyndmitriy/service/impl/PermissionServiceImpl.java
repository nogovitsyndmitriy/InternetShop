package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.PermissionDao;
import com.gmail.nogovitsyndmitriy.dao.impl.PermissionDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.PermissionService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.PermissionDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.PermissionConverter;
import com.gmail.nogovitsyndmitriy.service.model.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final static Logger log = LogManager.getLogger(PermissionServiceImpl.class);
    private final PermissionDtoConverter permissionDtoConverter;
    private final PermissionConverter permissionConverter;
    private PermissionDao permissionDao = new PermissionDaoImpl();
    private PermissionDto permissionDto = new PermissionDto();
    private Permission permission = new Permission();

    @Autowired
    public PermissionServiceImpl(@Qualifier("permissionDtoConverter") PermissionDtoConverter permissionDtoConverter, @Qualifier("permissionConverter") PermissionConverter permissionConverter) {
        this.permissionDtoConverter = permissionDtoConverter;
        this.permissionConverter = permissionConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public PermissionDto get(long id) {
        try {
            permission = permissionDao.get(id);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Get permission successful!");
        } catch (Exception e) {
            log.error("Get permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public PermissionDto save(PermissionDto dto) {
        try {
            permission = permissionConverter.toEntity(dto);
            permissionDao.save(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Saving permission successful!");
        } catch (Exception e) {
            log.error("Saving permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public PermissionDto update(PermissionDto dto) {
        try {
            permission = permissionConverter.toEntity(dto);
            permissionDao.update(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Update permission successful!");
        } catch (Exception e) {
            log.error("Update permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(PermissionDto dto) {
        try {
            permission = permissionConverter.toEntity(dto);
            permissionDao.delete(permission);
            log.info("Delete permission successful!");
        } catch (Exception e) {
            log.error("Delete permission failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            permission = permissionDao.get(id);
            permissionDao.delete(permission);
            log.info("Delete permission by Id successful!");
        } catch (Exception e) {
            log.error("Delete permission by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<PermissionDto> getAll() {
        return null;
    }
}
