package dao.impl;

import dao.GenericOrderDao;
import entities.GenericOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericOrderDaoImpl extends GenericAbstractDao<GenericOrder> implements GenericOrderDao {

    private static Logger log = LogManager.getLogger(GenericOrderDaoImpl.class);

    public GenericOrderDaoImpl(Class<GenericOrder> clazz) {
        super(clazz);
    }
}
