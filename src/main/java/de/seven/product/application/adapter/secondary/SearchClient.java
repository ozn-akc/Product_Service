package de.seven.product.application.adapter.secondary;

import de.seven.product.domain.model.Product;
import reactor.core.publisher.Mono;

public interface SearchClient {

    Mono<Product> saveProduct(Product product);

    Mono<Void> deleteProduct(String productId);
}
