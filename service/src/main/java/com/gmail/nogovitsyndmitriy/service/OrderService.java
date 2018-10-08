package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.OrderDto;

import java.util.List;

public interface OrderService extends GenericService<OrderDto> {
    List<OrderDto> ordersPagination(long page, int maxResult);

    long quantityOfOrders();

    List<OrderDto> findOrdersByUserId(long id);
}
