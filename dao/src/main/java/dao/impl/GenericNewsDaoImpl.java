package dao.impl;


import dao.GenericNewsDao;
import entities.GenericNews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenericNewsDaoImpl extends GenericAbstractDao<GenericNews> implements GenericNewsDao {
    private static Logger log = LogManager.getLogger(GenericNewsDaoImpl.class);
    public GenericNewsDaoImpl(Class<GenericNews> clazz){
        super(clazz);
    }
}
