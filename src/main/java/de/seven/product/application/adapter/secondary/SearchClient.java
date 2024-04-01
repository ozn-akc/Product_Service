package de.seven.product.application.adapter.secondary;

import de.seven.product.domain.model.Product;

import java.util.List;

public interface SearchClient {

    Product save(Product product);

    Product findById(Integer productId);

    List<Product> findAll();

    void delete(Product product);

    void delete(Integer productId);
}
