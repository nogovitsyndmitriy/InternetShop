package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.AuditDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Audit;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl() {
        super(Audit.class);
    }
}
