package de.seven.product.application.adapter.secondary;

import de.seven.product.domain.model.Product;

public interface SearchClient {

    Product save(Product product);

    void delete(Product product);

    void delete(Integer productId);
}
