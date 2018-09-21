package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.AuditDao;
import com.gmail.nogovitsyndmitriy.dao.impl.AuditDaoImpl;
import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gmail.nogovitsyndmitriy.service.AuditService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.AuditDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.AuditConverter;
import com.gmail.nogovitsyndmitriy.service.model.AuditDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuditServiceImpl implements AuditService {
    private final static Logger log = LogManager.getLogger(AuditServiceImpl.class);
    private AuditConverter auditConverter = new AuditConverter();
    private AuditDtoConverter auditDtoConverter = new AuditDtoConverter();
    private AuditDto auditDto = new AuditDto();
    private AuditDao auditDao = new AuditDaoImpl();
    private Audit audit = new Audit();


    @Override
    public AuditDto get(long id) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            audit = auditDao.get(id);
            auditDto = auditDtoConverter.toDTO(audit);
            transaction.commit();
            log.info("Getting audit by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Getting audit by Id failed!", e);
        }
        return auditDto;
    }

    @Override
    public AuditDto save(AuditDto dto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            audit = auditConverter.toEntity(dto);
            auditDao.save(audit);
            auditDto = auditDtoConverter.toDTO(audit);
            transaction.commit();
            log.info("Saving audit successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving audit failed!", e);
        }
        return auditDto;
    }

    @Override
    public AuditDto update(AuditDto dto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            audit = auditConverter.toEntity(dto);
            auditDao.update(audit);
            auditDto = auditDtoConverter.toDTO(audit);
            transaction.commit();
            log.info("Update audit successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update audit failed!", e);
        }
        return auditDto;
    }

    @Override
    public void delete(AuditDto dto) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            audit = auditConverter.toEntity(dto);
            auditDao.delete(audit);
            transaction.commit();
            log.info("Delete audit successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete audit failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            audit = auditDao.get(id);
            auditDao.delete(audit);
            transaction.commit();
            log.info("Delete by Id audit successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete by Id audit failed!", e);
        }
    }

    @Override
    public List<AuditDto> getAll() {
        return null;
    }
}
