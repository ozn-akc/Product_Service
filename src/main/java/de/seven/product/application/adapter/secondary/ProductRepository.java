package de.seven.product.application.adapter.secondary;

import de.seven.product.domain.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    Product insertProduct(Map<String, String> Product);

    Product updateProduct(Product Product);

    Product findProductById(Integer ProductId);

    List<Product> findProduct(Map<String, String> Product);

    void deleteProduct(Product Product);

    void deleteProduct(Integer ProductId);

}
