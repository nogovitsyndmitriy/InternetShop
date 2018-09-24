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
    public PermissionDto get(long id) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            permission = permissionDao.get(id);
            permissionDto = permissionDtoConverter.toDTO(permission);
            transaction.commit();
            log.info("Get permission successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    public PermissionDto save(PermissionDto dto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            permission = permissionConverter.toEntity(dto);
            permissionDao.save(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            transaction.commit();
            log.info("Saving permission successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    public PermissionDto update(PermissionDto dto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            permission = permissionConverter.toEntity(dto);
            permissionDao.update(permission);
            permissionDto = permissionDtoConverter.toDTO(permission);
            transaction.commit();
            log.info("Update permission successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update permission failed!", e);
        }
        return permissionDto;
    }

    @Override
    public void delete(PermissionDto dto) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            permission = permissionConverter.toEntity(dto);
            permissionDao.delete(permission);
            transaction.commit();
            log.info("Delete permission successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete permission failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = permissionDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            permission = permissionDao.get(id);
            permissionDao.delete(permission);
            transaction.commit();
            log.info("Delete permission by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete permission by Id failed!", e);
        }
    }

    @Override
    public List<PermissionDto> getAll() {
        return null;
    }
}
