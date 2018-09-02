package dao.impl;

import dao.GenericItemDao;
import entities.GenericItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericItemDaoImpl extends GenericAbstractDao<GenericItem> implements GenericItemDao {

    private static Logger log = LogManager.getLogger(GenericItemDaoImpl.class);

    public GenericItemDaoImpl(Class<GenericItem> clazz) {
        super(clazz);
    }

}
