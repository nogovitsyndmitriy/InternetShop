package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    private final static Logger log = LogManager.getLogger(ItemDaoImpl.class);

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }
}
