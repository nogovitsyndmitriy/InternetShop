package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.AuditDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import com.gmail.nogovitsyndmitriy.service.AuditService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.AuditDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.AuditConverter;
import com.gmail.nogovitsyndmitriy.service.model.AuditDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final static Logger log = LogManager.getLogger(AuditServiceImpl.class);
    private final AuditDao auditDao;
    private final AuditConverter auditConverter;
    private final AuditDtoConverter auditDtoConverter;

    @Autowired
    public AuditServiceImpl(@Qualifier("auditConverter") AuditConverter auditConverter,
                            @Qualifier("auditDtoConverter") AuditDtoConverter auditDtoConverter,
                            AuditDao auditDao) {
        this.auditConverter = auditConverter;
        this.auditDtoConverter = auditDtoConverter;
        this.auditDao = auditDao;
    }


    @Override
    @Transactional
    public AuditDto get(Long id) {
        AuditDto auditDto = new AuditDto();
        try {
            Audit audit = auditDao.get(id);
            auditDto = auditDtoConverter.toDTO(audit);
            log.info("Getting audit by Id successful!");
        } catch (Exception e) {
            log.error("Getting audit by Id failed!", e);
        }
        return auditDto;
    }

    @Override
    @Transactional
    public AuditDto save(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.save(audit);
            auditDto = auditDtoConverter.toDTO(audit);
            log.info("Saving audit successful!");
        } catch (Exception e) {
            log.error("Saving audit failed!", e);
        }
        return auditDto;
    }

    @Override
    @Transactional
    public AuditDto update(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.update(audit);
            auditDto = auditDtoConverter.toDTO(audit);
            log.info("Update audit successful!");
        } catch (Exception e) {
            log.error("Update audit failed!", e);
        }
        return auditDto;
    }

    @Override
    @Transactional
    public void delete(AuditDto auditDto) {
        try {
            Audit audit = auditConverter.toEntity(auditDto);
            auditDao.delete(audit);
            log.info("Delete audit successful!");
        } catch (Exception e) {
            log.error("Delete audit failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            Audit audit = auditDao.get(id);
            auditDao.delete(audit);
            log.info("Delete by Id audit successful!");
        } catch (Exception e) {
            log.error("Delete by Id audit failed!", e);
        }
    }

    @Override
    @Transactional
    public List<AuditDto> getAll() {
        return new ArrayList<>();
    }
}
