package de.seven.product.adapter.primary;

import de.seven.product.application.service.ProductService;
import de.seven.product.domain.model.*;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProducts(@PathVariable String productId){
        return productService.findProductById(productId);
    }

    @PostMapping("/")
    public Product saveProducts(@RequestBody Product product){
        /*
        Product product = Product.builder()
                .images(List.of("test123", "test2", "test3"))
                .beds(List.of(Bed.builder().amount(1).type(BedType.SCHLAFCOUCH).build(),Bed.builder().amount(2).type(BedType.QUEENSIZE_DOPPELBETT).build()))
                .attributes(List.of(Attribute.TV))
                .reviews(List.of(Review.builder().comment("test").score(5).userId("UserId12345").build()))
                .rentedDays(List.of(Date.from(Instant.now())))
                .price(Price.builder().value(100.0).unit(Unit.DOLLAR).build())
                .address(Address.builder().street("Street").city("City").country("Country").number("Product").build())
                .build();
        */
        return productService.insertProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable String productId){
        productService.deleteProduct(productId);
    }
}
