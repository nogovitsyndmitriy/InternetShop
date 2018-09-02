package dao.impl;

import entities.Order;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoImpl extends AbstractDao implements dao.OrderDao {

    private static volatile OrderDaoImpl INSTANCE = null;

    private static final String getByUserIdQuerry = "SELECT * FROM e_shop.order WHERE User_Id = ?";
    private static final String saveOrderQuery = "INSERT INTO e_shop.order (User_Id, Total) VALUES (?, ?)";
    private static final String getOrderQuery = "SELECT * FROM e_shop.order WHERE Order_Id = ?";
    private static final String updateOrderQuery = "UPDATE e_shop.order SET User_Id = ?, Total = ?";
    private static final String deleteOrderQuery = "DELETE FROM e_shop.order WHERE Order_Id = ?";
    private static final String getAllOrderQuery = "SELECT * FROM e_shop.order";

    PreparedStatement psGetByUserIdQuery;
    PreparedStatement psSave;
    PreparedStatement psGet;
    PreparedStatement psUpdate;
    PreparedStatement psDelete;
    PreparedStatement psGetAll;

    public static OrderDaoImpl getINSTANCE() {
        OrderDaoImpl orderDao = INSTANCE;
        if (orderDao == null) {
            synchronized (OrderDaoImpl.class) {
                orderDao = INSTANCE;
                if (orderDao == null) {
                    INSTANCE = orderDao = new OrderDaoImpl();
                }
            }
        }
        return orderDao;
    }


    @Override
    public Order getByUserId(Serializable id) throws SQLException {
        psGetByUserIdQuery = preparedStatement(getByUserIdQuerry);
        psGetByUserIdQuery.setLong(1, (long) id);
        psGetByUserIdQuery.executeQuery();
        ResultSet rs = psGetByUserIdQuery.getResultSet();
        while (rs.next()) {

        }
        return null;
    }

    @Override
    public Order save(Order order) throws SQLException {
        psSave = preparedStatement(saveOrderQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1,order.getUserId());
        psSave.setDouble(2, order.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        while (rs.next()){
            order.setId(rs.getLong(1));
        }
        close(rs);
        return order;
    }

    @Override
    public Order get(Serializable id) throws SQLException {
        psGet = preparedStatement(getOrderQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        while(rs.next()){
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> list = new LinkedList<>();
        psGetAll = preparedStatement(getAllOrderQuery);
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()){
            Order order = new Order();
            order.setId(rs.getLong(1));
            order.setUserId(rs.getLong(2));
            order.setTotal(rs.getDouble(3));
            list.add(order);
        }
        return list;
    }

    @Override
    public void update(Order order) throws SQLException {
        psUpdate = preparedStatement(updateOrderQuery);
        psUpdate.setLong(1, order.getUserId());
        psUpdate.setDouble(2, order.getTotal());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteOrderQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
    private static Order populateEntity(ResultSet rs) throws SQLException {
        Order entity = new Order();
        entity.setId(rs.getLong(1));
        entity.setUserId(rs.getLong(2));
        entity.setTotal(rs.getDouble(3));
        return entity;
    }
}
