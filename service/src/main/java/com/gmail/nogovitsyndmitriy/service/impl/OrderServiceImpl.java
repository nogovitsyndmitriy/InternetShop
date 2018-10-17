package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.OrderDao;
import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.OrderDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.OrderConverter;
import com.gmail.nogovitsyndmitriy.service.exceptions.ServiceException;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.utils.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    private final UserDao userDao;
    private final ItemDao itemDao;

    @Autowired
    public OrderServiceImpl(@Qualifier("orderDtoConverter") OrderDtoConverter orderDtoConverter, @Qualifier("orderConverter") OrderConverter orderConverter, OrderDao orderDao, UserDao userDao, ItemDao itemDao) {
        this.orderDtoConverter = orderDtoConverter;
        this.orderConverter = orderConverter;
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto get(Long id) {
        OrderDto orderDto = new OrderDto();
        if (orderDto != null) {
            Order order = orderDao.get(id);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Get order successful!");
        } else {
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto, Long itemId, Integer quantity) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            order.setUser(userDao.get(CurrentUserUtil.getCurrentUser().getId()));
            if (quantity <= 0) {
                quantity = 1;
            }
            order.setQuantity(quantity);
            order.setItem(itemDao.get(itemId));
            order.setStatus(Status.NEW);
            order.setCreated(LocalDateTime.now());
            orderDao.save(order);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Saving order successful!");
        } catch (Exception e) {
            log.error("Saving order failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.update(order);
            orderDto = orderDtoConverter.toDTO(order);
            log.info("Update order successful!");
        } catch (Exception e) {
            log.error("Update order failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return orderDto;
    }

    @Override
    @Transactional
    public void delete(OrderDto orderDto) {
        try {
            Order order = orderConverter.toEntity(orderDto);
            orderDao.delete(order);
            log.info("Delete order successful!");
        } catch (Exception e) {
            log.error("Delete order failed!", e);
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Order order = orderDao.get(id);
        if (order != null) {
            orderDao.save(order);
            log.info("Delete order by Id successful!");
        } else {
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
        }
    }

    @Override
    @Transactional
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
            throw new ServiceException("Service Exception!");
        }
        return ordersDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Long quantityOfOrders() {
        Long quantity;
        try {
            quantity = orderDao.quantityOfOrders();
            log.info("Quantity find successful!");
        } catch (Exception e) {
            log.error("Failed to count quantity!", e);
            throw new ServiceException("Service Exception!");
        }
        return quantity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByUserId(Long id) {
        List<Order> list = orderDao.findOrdersByUserId(id);
        List<OrderDto> orders = list.stream().map(orderDtoConverter::toDTO).collect(Collectors.toCollection(LinkedList::new));
        return orders;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAll() {
        List<Order> orders = orderDao.getAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            orderDtos.add(orderDtoConverter.toDTO(order));
        }
        return orderDtos;
    }

    @Override
    @Transactional(readOnly = true)
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
            throw new ServiceException("Service Exception!");
        }
        return ordersDto;
    }

    @Override
    public OrderDto save(OrderDto dto) {
        throw new UnsupportedOperationException();
    }
}
