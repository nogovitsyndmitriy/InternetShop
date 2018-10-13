package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.PermissionDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Permission;
import com.gmail.nogovitsyndmitriy.service.PermissionService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.PermissionDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.PermissionConverter;
import com.gmail.nogovitsyndmitriy.service.model.PermissionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final static Logger log = LogManager.getLogger(PermissionServiceImpl.class);
    private final PermissionDtoConverter permissionDtoConverter;
    private final PermissionConverter permissionConverter;
    private final PermissionDao permissionDao;


    @Autowired
    public PermissionServiceImpl(@Qualifier("permissionDtoConverter") PermissionDtoConverter permissionDtoConverter,
                                 @Qualifier("permissionConverter") PermissionConverter permissionConverter,
                                 PermissionDao permissionDao) {
        this.permissionDtoConverter = permissionDtoConverter;
        this.permissionConverter = permissionConverter;
        this.permissionDao = permissionDao;
    }

    @Override
    @Transactional
    public PermissionDto get(Long id) {
        PermissionDto permissionDto = new PermissionDto();
        try {
            Permission permission = permissionDao.get(id);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Get permission successful!");
        } catch (Exception e) {
            log.error("Get permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public PermissionDto save(PermissionDto permissionDto) {
        try {
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.save(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Saving permission successful!");
        } catch (Exception e) {
            log.error("Saving permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public PermissionDto update(PermissionDto permissionDto) {
        try {
            Permission permission = permissionConverter.toEntity(permissionDto);
            permissionDao.update(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            log.info("Update permission successful!");
        } catch (Exception e) {
            log.error("Update permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    @Transactional
    public void delete(PermissionDto dto) {
        try {
            Permission permission = permissionConverter.toEntity(dto);
            permissionDao.delete(permission);
            log.info("Delete permission successful!");
        } catch (Exception e) {
            log.error("Delete permission failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Permission permission = permissionDao.get(id);
            permissionDao.delete(permission);
            log.info("Delete permission by Id successful!");
        } catch (Exception e) {
            log.error("Delete permission by Id failed!", e);
        }
    }

    @Override
    @Transactional
    public List<PermissionDto> getAll() {
        return new ArrayList<>();
    }
}
