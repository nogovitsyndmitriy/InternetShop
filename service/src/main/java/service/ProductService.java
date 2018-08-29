package service;

import entities.Product;

import java.io.Serializable;
import java.util.List;

public interface ProductService {
    Product getProductByCategory(String category);

    Product save(Product product);

    Product get(Serializable id);

    void update(Product product);

    int delete(Serializable id);

    List<Product> getAll();
}
