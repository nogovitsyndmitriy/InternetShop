package dao.impl;

import dao.ItemDao;
import entities.Item;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemDaoImpl extends AbstractDao implements ItemDao {

    private static volatile ItemDao INSTANCE = null;

    private static final String saveItemQuery = "INSERT INTO e_shop.item (Product_Name, Quantity, Product_Id, User_Id, Order_Id, Price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String getItemQuery = "SELECT * FROM e_shop.item WHERE Item_Id = ?";
    private static final String updateItemQuery = "UPDATE e_shop.item SET Product_Name = ?, Quantity = ?, Order_Id = ?, User_Id = ?";
    private static final String deleteItemQuery = "DELETE FROM e_shop.item WHERE Item_Id = ?";
    private static final String getAllItemQuery = "SELECT * FROM e_shop.item";
    private static final String getAllByUserIdQuery = "SELECT* FROM e_shop.item WHERE User_Id = ?";

    PreparedStatement psSave;
    PreparedStatement psGet;
    PreparedStatement psUpdate;
    PreparedStatement psDelete;
    PreparedStatement psGetAll;
    PreparedStatement psGetAllByUserId;

    public static ItemDao getINSTANCE() {
        ItemDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDaoImpl.class) {
                itemDao = INSTANCE;
            }
            if (itemDao == null) {
                INSTANCE = itemDao = new ItemDaoImpl();
            }
        }
        return itemDao;
    }

    @Override
    public Item save(Item item) throws SQLException {
        psSave = preparedStatement(saveItemQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, item.getProductName());
        psSave.setInt(2, item.getQuantity());
        psSave.setLong(3, item.getUserId());
        psSave.setLong(4, item.getOrderId());
        psSave.setLong(5, item.getProductId());
        psSave.setDouble(6, item.getPrice());

        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        while (rs.next()) {
            item.setId(rs.getLong(1));
        }
        close(rs);
        return item;
    }

    @Override
    public Item get(Serializable id) throws SQLException {
        psGet = preparedStatement(getItemQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return populateEntity(rs);
    }

    @Override
    public void update(Item item) throws SQLException {
        psUpdate = preparedStatement(updateItemQuery);
        psUpdate.setString(1, item.getProductName());
        psUpdate.setInt(2, item.getQuantity());
        psUpdate.setLong(3, item.getOrderId());
        psUpdate.setLong(4, item.getUserId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteItemQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private static Item populateEntity(ResultSet rs) throws SQLException {
        Item entity = new Item();
        entity.setId(rs.getLong(1));
        entity.setProductName(rs.getString(2));
        entity.setQuantity(rs.getInt(3));
        entity.setUserId(rs.getLong(4));
        entity.setProductId(rs.getLong(5));
        entity.setOrderId(rs.getLong(6));
        entity.setPrice(rs.getDouble(7));
        return entity;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        psGetAll = preparedStatement(getAllItemQuery);
        List<Item> list = new ArrayList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getLong(1));
            item.setQuantity(rs.getInt(2));
            item.setProductName(rs.getString(3));
            item.setProductId(rs.getLong(4));
            item.setOrderId(rs.getLong(6));
            item.setPrice(rs.getDouble(7));
            item.setUserId(rs.getLong(5));
            list.add(item);
        }
        close(rs);
        return list;
    }

    @Override
    public List<Item> getAllByUserId(Serializable id) throws SQLException {
        psGetAllByUserId = preparedStatement(getAllByUserIdQuery);
        psGetAllByUserId.setLong(1, (long) id);
        psGetAllByUserId.executeQuery();
        List<Item> list = new LinkedList<>();
        ResultSet rs = psGetAllByUserId.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
}
