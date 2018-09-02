package dao.impl;

import dao.GenericAuditDao;
import entities.GenericAudit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericAuditDaoImpl extends GenericAbstractDao<GenericAudit> implements GenericAuditDao {

    private static Logger log = LogManager.getLogger(GenericAuditDaoImpl.class);

    public GenericAuditDaoImpl(Class<GenericAudit> clazz) {
        super(clazz);
    }

}
