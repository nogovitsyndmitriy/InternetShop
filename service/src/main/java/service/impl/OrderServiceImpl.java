package service.impl;

import dao.ItemDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;
import entities.Item;
import entities.Order;
import entities.Product;
import service.AbstractService;
import service.OrderService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderServiceImpl extends AbstractService implements OrderService {
    private static volatile OrderService INSTANCE = null;
    private OrderDao orderDao = OrderDaoImpl.getINSTANCE();
    private ProductDao productDao = ProductDaoImpl.getINSTANCE();
    private ItemDao itemDao = ItemDaoImpl.getINSTANCE();
    Order order = new Order();


    public static OrderService getINSTANCE() {
        OrderService orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }
        return orderService;
    }

    @Override
    public List<Order> getAll() {
        List<Order> list = new LinkedList<>();
        try {
            startTransaction();
            list = orderDao.getAll();
            commit();
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
        return list;
    }

    @Override
    public Order getByUserId(Serializable id) {
        try {
            startTransaction();
            order = orderDao.getByUserId(id);
            commit();
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
        return order;
    }

    @Override
    public Order save(Order order) {
        try {
            startTransaction();
            order = orderDao.save(order);
            commit();
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
        return order;
    }

    @Override
    public Order get(Serializable id) {
        try {
            startTransaction();
            order = orderDao.get(id);
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
        return order;
    }

    @Override
    public void update(Order order) {
        try {
            startTransaction();
            orderDao.update(order);
            commit();
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            orderDao.delete(id);
            commit();
        } catch (SQLException e) {
            log.warning(String.valueOf(e));
            rollback();
        }
        return 0;
    }

    @Override
    public Order createOrder(long userId, long productId, int quantity) {
        try {
            startTransaction();
            order.setUserId(userId);

            Product product = productDao.get(productId);

            order.setTotal(product.getPrice() * quantity);
            orderDao.save(order);

//            Item item = new Item(product.getName(), productId, quantity, order.getId(), userId);
//            itemDao.save(item);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
        return order;
    }
}
