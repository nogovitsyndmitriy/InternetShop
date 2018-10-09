package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;

import java.util.List;

public interface OrderService extends GenericService<OrderDto> {
    List<OrderDto> ordersPagination(long page, int maxResult);

    long quantityOfOrders();

    List<OrderDto> findOrdersByUserId(long id);

    List<OrderDto> ordersPanginationById(long page, int maxResult, long id);
}
