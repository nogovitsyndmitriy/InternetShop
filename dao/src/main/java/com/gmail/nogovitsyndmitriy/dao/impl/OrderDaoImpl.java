package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.OrderDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private final static Logger log = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }
}
