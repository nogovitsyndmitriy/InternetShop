package dao;

import entities.Basket;
import entities.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BasketDao extends DAO<Basket> {

    List<Basket> getByUserId (Serializable id) throws SQLException;
}
