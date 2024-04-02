package de.seven.product.application.service;

import de.seven.product.application.adapter.secondary.ProductRepository;
import de.seven.product.domain.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String productId) {
        productRepository.delete(productId);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
