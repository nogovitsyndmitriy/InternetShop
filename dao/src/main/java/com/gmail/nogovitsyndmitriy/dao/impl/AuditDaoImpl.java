package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.AuditDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("")
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    private final static Logger log = LogManager.getLogger(AuditDaoImpl.class);

    public AuditDaoImpl() {
        super(Audit.class);
    }
}
