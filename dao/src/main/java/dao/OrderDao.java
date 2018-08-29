package dao;

import entities.Order;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends DAO<Order>{
    Order getByUserId(Serializable id) throws SQLException;
    List<Order> getAll() throws SQLException;

}
