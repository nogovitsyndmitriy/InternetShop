package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.OrderDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.impl.OrderDaoImpl;
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

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderDtoConverter orderDtoConverter;
    private final OrderConverter orderConverter;
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderDto orderDto = new OrderDto();
    private Order order = new Order();

    @Autowired
    public OrderServiceImpl(@Qualifier("orderDtoConverter") OrderDtoConverter orderDtoConverter, @Qualifier("orderConverter") OrderConverter orderConverter) {
        this.orderDtoConverter = orderDtoConverter;
        this.orderConverter = orderConverter;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto get(long id) {
        try {
            orderDao.get(id);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Get order successful!");
        } catch (Exception e) {
            log.error("Get order failed!", e);
        }
        return orderDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public OrderDto save(OrderDto dto) {
        try {
            order = orderConverter.toEntity(dto);
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
    public OrderDto update(OrderDto dto) {
        try {
            order = orderConverter.toEntity(dto);
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
    public void delete(OrderDto dto) {
        try {
            order = orderConverter.toEntity(dto);
            orderDao.delete(order);
            log.info("Delete order successful!");
        } catch (Exception e) {
            log.error("Delete order failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            order = orderDao.get(id);
            orderDao.save(order);
            log.info("Delete order by Id successful!");
        } catch (Exception e) {
            log.error("Delete order by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<OrderDto> getAll() {
        return null;
    }
}
