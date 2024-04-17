package de.seven.product.application.service;

import de.seven.product.application.adapter.secondary.ProductRepository;
import de.seven.product.application.adapter.secondary.SearchClient;
import de.seven.product.domain.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final SearchClient searchClient;

    @Autowired
    public ProductService(ProductRepository productRepository, SearchClient searchClient) {
        this.productRepository = productRepository;
        this.searchClient = searchClient;
    }

    public Product insertProduct(Product product) {
        Product result = productRepository.save(product);
        searchClient.saveProduct(result).block();
        return result;
    }

    public void deleteProduct(String productId) {
        productRepository.delete(productId);
        searchClient.deleteProduct(productId).block();
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public String insertHost(String host) {
        return productRepository.saveHost(host);
    }

    public void deleteHost(String hostId) {
        productRepository.deleteHost(hostId);
    }

}
