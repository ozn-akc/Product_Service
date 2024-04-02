package de.seven.product.adapter.primary;

import de.seven.product.application.service.ProductService;
import de.seven.product.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProducts(@PathVariable String productId) {
        return productService.findProductById(productId);
    }

    @PostMapping("/")
    public Product saveProducts(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }
}
