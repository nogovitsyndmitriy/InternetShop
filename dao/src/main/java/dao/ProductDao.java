package dao;

import entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends DAO<Product>{
    Product getProductByCategory(String category) throws SQLException;

}
