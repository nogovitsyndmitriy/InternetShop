package dao.impl;

import dao.BasketDao;
import entities.Basket;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class BasketDaoImpl extends AbstractDao implements BasketDao {

    private static volatile BasketDao INSTANCE = null;

    private static final String getAllBasketQuery = "SELECT * FROM e_shop.basket";
    private static final String getBasketByUserIdQuery = "SELECT * FROM e_shop.basket WHERE User_Id = ?";
    private static final String saveBasketQuery = "INSERT INTO e_shop.basket (Product_Name, User_Id, Order_Id, Quantity)";


    @Override
    public List<Basket> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Basket> getByUserId(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public Basket save(Basket basket) throws SQLException {
        return null;
    }

    @Override
    public Basket get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(Basket basket) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
}
