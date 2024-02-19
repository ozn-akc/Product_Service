package de.seven.product.application.service;

import de.seven.product.application.adapter.secondary.ProductRepository;
import de.seven.product.domain.model.Product;

import java.util.List;
import java.util.Map;

public class ProductService {
    //TODO Hier den Adapter einsetzen
    private ProductRepository productRepository;

    public ProductService(){
        this.productRepository = productRepository;
    }

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public
    Product insertProduct(Map<String, String> product){
        return productRepository.insertProduct(product);
    }

    public Product updateProduct(Product product){
        return productRepository.updateProduct(product);
    }

    public void deleteProduct(Product product){
        productRepository.deleteProduct(product);
    }

    public void deleteProduct(Integer productId){
        productRepository.deleteProduct(productId);
    }

    public Product findProductById(Integer productId){
        return productRepository.findProductById(productId);
    }

    public List<Product> findProduct(Map<String, String> product){
        return productRepository.findProduct(product);
    }

}
