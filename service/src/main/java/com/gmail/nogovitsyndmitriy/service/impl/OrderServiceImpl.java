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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    public OrderDto get(long id) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            orderDao.get(id);
            orderDto = orderDtoConverter.toDTO(order);
            transaction.commit();
            log.info("Get order successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get order failed!", e);
        }
        return orderDto;
    }

    @Override
    public OrderDto save(OrderDto dto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            order = orderConverter.toEntity(dto);
            orderDao.save(order);
            orderDto = orderDtoConverter.toDTO(order);
            transaction.commit();
            log.info("Saving order successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving order failed!", e);
        }
        return orderDto;
    }

    @Override
    public OrderDto update(OrderDto dto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            order = orderConverter.toEntity(dto);
            orderDao.update(order);
            orderDto = orderDtoConverter.toDTO(order);
            transaction.commit();
            log.info("Update order successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update order failed!", e);
        }
        return orderDto;
    }

    @Override
    public void delete(OrderDto dto) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            order = orderConverter.toEntity(dto);
            orderDao.delete(order);
            transaction.commit();
            log.info("Delete order successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete order failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = orderDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            order = orderDao.get(id);
            orderDao.save(order);
            transaction.commit();
            log.info("Delete order by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete order by Id failed!", e);
        }
    }

    @Override
    public List<OrderDto> getAll() {
        return null;
    }
}
