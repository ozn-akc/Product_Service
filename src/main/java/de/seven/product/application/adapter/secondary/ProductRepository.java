package de.seven.product.application.adapter.secondary;

import de.seven.product.adapter.secondary.postgresql.model.HostDTO;
import de.seven.product.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    String save(String host);

    Product findById(String productId);

    List<Product> findAll();

    void delete(String productId);

}
