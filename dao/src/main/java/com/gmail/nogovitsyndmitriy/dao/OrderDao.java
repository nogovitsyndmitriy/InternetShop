package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findOrdersByUserId(long userId);

    List<Order> ordersPangination(long page, int maxResult);

    long quantityOfOrders();
}
