package dao.impl;

import dao.ProductDao;
import entities.Product;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao implements ProductDao {

    private static volatile ProductDao INSTANCE = null;

    private static final String getProductByCategoryQuery = "SELECT * FROM e_shop.product WHERE Category = ?";
    private static final String saveProductQuery = "INSERT INTO e_shop.product (Category, Name, Price) VALUES (?, ?, ?)";
    private static final String getProductQuery = "SELECT * FROM e_shop.product WHERE Product_Id = ?";
    private static final String updateProductQuery = "UPDATE e_shop.product SET Category = ?, Name = ?, Price = ?  WHERE Product_Id = ?";
    private static final String deleteProductQuery = "DELETE FROM e_shop.product WHERE Product_Id = ?";
    private static final String getAllProductsQuery = "SELECT * FROM e_shop.product";

    PreparedStatement psGetByCategory;
    PreparedStatement psSave;
    PreparedStatement psGet;
    PreparedStatement psUpdate;
    PreparedStatement psDelete;
    PreparedStatement psGetAll;


    public static ProductDao getINSTANCE() {
        ProductDao productDao = INSTANCE;
        if (productDao == null) {
            synchronized (ProductDaoImpl.class) {
                productDao = INSTANCE;
                if (productDao == null) {
                    INSTANCE = productDao = new ProductDaoImpl();
                }
            }
        }
        return productDao;
    }

    @Override
    public Product getProductByCategory(String category) throws SQLException {

        psGetByCategory = preparedStatement(getProductByCategoryQuery);
        psGetByCategory.setString(1, category);
        psGetByCategory.executeQuery();
        ResultSet rs = psGetByCategory.getResultSet();
        Product product = new Product();
        while (rs.next()) {
            product.setId(rs.getLong(1));
            product.setCategory(rs.getString(2));
            product.setName(rs.getString(3));
            product.setPrice(rs.getDouble(4));
        }
        close(rs);
        return product;
    }

    @Override
    public Product save(Product product) throws SQLException {
        psSave = preparedStatement(saveProductQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, product.getCategory());
        psSave.setString(2, product.getName());
        psSave.setDouble(3, product.getPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            product.setId(rs.getLong(1));
        }
        close(rs);
        return product;
    }

    @Override
    public Product get(Serializable id) throws SQLException {
        psGet = preparedStatement(getProductQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        while (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Product product) throws SQLException {
        psUpdate = preparedStatement(updateProductQuery);
        psUpdate.setLong(1,product.getId());
        psUpdate.setString(2, product.getCategory());
        psUpdate.setString(3, product.getName());
        psUpdate.setDouble(4, product.getPrice());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteProductQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private Product populateEntity(ResultSet rs) throws SQLException {
        Product entity = new Product();
        entity.setId(rs.getLong(1));
        entity.setCategory(rs.getString(2));
        entity.setName(rs.getString(3));
        entity.setPrice(rs.getDouble(4));
        return entity;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        psGetAll = preparedStatement(getAllProductsQuery);
        List<Product> list = new LinkedList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getLong(1));
            product.setCategory(rs.getString(2));
            product.setName(rs.getString(3));
            product.setPrice(rs.getDouble(4));
            list.add(product);
        }
        close(rs);
        return list;
    }
}
