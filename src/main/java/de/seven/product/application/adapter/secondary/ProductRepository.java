package de.seven.product.application.adapter.secondary;

import de.seven.product.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    String saveHost(String host);

    Product findById(String productId);

    List<Product> findAll();

    void delete(String productId);

    void deleteHost(String hostId);
}
