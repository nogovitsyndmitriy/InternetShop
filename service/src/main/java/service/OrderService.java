package service;

import entities.Order;

import java.io.Serializable;
import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getByUserId(Serializable id);

    Order save(Order order);

    Order get(Serializable id);

    void update(Order order);

    int delete(Serializable id);

    Order createOrder(long userId, long productId, int quantity);
}
