package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService extends GenericService<OrderDto> {
    @Transactional
    OrderDto save(OrderDto orderDto, Long itemId, Integer quantity);

    List<OrderDto> ordersPagination(Long page, int maxResult);

    Long quantityOfOrders();

    List<OrderDto> findOrdersByUserId(Long id);

    List<OrderDto> ordersPanginationById(Long page, int maxResult, Long id);
}
