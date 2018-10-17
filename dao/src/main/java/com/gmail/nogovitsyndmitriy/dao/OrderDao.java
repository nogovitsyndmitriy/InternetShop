package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findOrdersByUserId(Long userId);

    List<Order> ordersPangination(Long page, int maxResult);

    List<Order> ordersPanginationById(Long page, int maxResult, Long id);

    Long quantityOfOrders();
}
