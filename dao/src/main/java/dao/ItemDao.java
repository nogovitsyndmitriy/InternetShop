package dao;

import entities.Item;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends DAO<Item> {
    List<Item> getAll() throws SQLException;

    List<Item> getAllByUserId(Serializable id) throws SQLException;
}
