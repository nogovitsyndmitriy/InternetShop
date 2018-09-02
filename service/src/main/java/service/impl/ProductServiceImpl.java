package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entities.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AbstractService;
import service.ProductService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductServiceImpl extends AbstractService implements ProductService {
    private static volatile ProductService INSTANCE = null;
    private ProductDao productDao = ProductDaoImpl.getINSTANCE();
    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);
    Product product = new Product();

    public static ProductService getINSTANCE() {
        ProductService productService = INSTANCE;
        if (productService == null) {
            synchronized (ProductDaoImpl.class) {
                productService = INSTANCE;
                if (productService == null) {
                    INSTANCE = productService = new ProductServiceImpl();
                }
            }
        }
        return productService;
    }

    @Override
    public Product getProductByCategory(String category) {
        try {
            startTransaction();
            product = productDao.getProductByCategory(category);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return product;
    }

    @Override
    public Product save(Product product) {
        try {
            startTransaction();
            product = productDao.save(product);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return product;
    }

    @Override
    public Product get(Serializable id) {
        try {
            startTransaction();
            product = productDao.get(id);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try {
            startTransaction();
            productDao.update(product);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }

    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            productDao.delete(id);
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return 0;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new LinkedList<>();
        try {
            startTransaction();
            list = productDao.getAll();
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return list;
    }
}
