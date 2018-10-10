package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.OrderDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.OrderDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.OrderConverter;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderDtoConverter orderDtoConverter;
    private final OrderConverter orderConverter;
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(@Qualifier("orderDtoConverter") OrderDtoConverter orderDtoConverter, @Qualifier("orderConverter") OrderConverter orderConverter, OrderDao orderDao) {
        this.orderDtoConverter = orderDtoConverter;
        this.orderConverter = orderConverter;
        this.orderDao = orderDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto get(Long id) {
        OrderDto orderDto = new OrderDto();
        try {
            Order order = orderDao.get(id);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Get order successful!");
        } catch (Exception e) {
            log.error("Get order failed!", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto save(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            order.setStatus(Status.NEW);
            order.setCreated(LocalDateTime.now());
            orderDao.save(order);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Saving order successful!");
        } catch (Exception e) {
            log.error("Saving order failed!", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto update(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.update(order);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Update order successful!");
        } catch (Exception e) {
            log.error("Update order failed!", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.delete(order);
            log.info("Delete order successful!");
        } catch (Exception e) {
            log.error("Delete order failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            Order order = orderDao.get(id);
            orderDao.save(order);
            log.info("Delete order by Id successful!");
        } catch (Exception e) {
            log.error("Delete order by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> ordersPagination(Long page, int maxResult) {
        List<OrderDto> ordersDto = new ArrayList<>();
        try {
            List<Order> orders = orderDao.ordersPangination(page, maxResult);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDTO(order));
            }
            log.info("Order pangination successful!");
        } catch (Exception e) {
            log.error("Failed to get orders pangination");
        }
        return ordersDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Long quantityOfOrders() {
        Long quantity = 0L;
        try {
            quantity = orderDao.quantityOfOrders();
            log.info("Quantity find successful!");
        } catch (Exception e) {
            log.error("Failed to count quantity!", e);
        }
        return quantity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> findOrdersByUserId(Long id) {
        List<Order> list = orderDao.findOrdersByUserId(id);
        List<OrderDto> orders = list.stream().map(orderDtoConverter::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return orders;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> getAll() {
        return new LinkedList<>();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> ordersPanginationById(Long page, int maxResult, Long id) {
        List<OrderDto> ordersDto = new LinkedList<>();
        try {
            List<Order> orders = orderDao.ordersPanginationById(page, maxResult, id);
            for (Order order : orders) {
                ordersDto.add(orderDtoConverter.toDTO(order));
            }
            log.info("Order pangination successful!");
        } catch (Exception e) {
            log.error("Failed to get orders pangination");
        }
        return ordersDto;
    }
}
