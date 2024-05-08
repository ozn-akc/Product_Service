package de.seven.product.adapter.secondary.mongodb.model;

import de.seven.product.domain.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;
    String name;
    HostDTO host;
    List<String> images;
    Integer bedrooms;
    Integer bathrooms;
    String description;
    Price price;
    Address address;
    List<Bed> beds;
    List<Attribute> attributes;
    List<LocalDate> rentedDays;
    List<Review> reviews;

    public Product toDomainProduct() {
        return Product.builder()
                .productId(productId)
                .name(name)
                .hostId(Optional.ofNullable(host)
                        .map(HostDTO::toDomainHost)
                        .orElse(null))
                .images(images)
                .bedrooms(bedrooms)
                .bathrooms(bathrooms)
                .description(description)
                .price(price)
                .beds(beds)
                .attributes(attributes)
                .rentedDays(rentedDays)
                .reviews(reviews)
                .build();
    }

    public static ProductDTO fromDomainProduct(Product domainProduct) {
        ProductDTO product = ProductDTO.builder()
                .productId(domainProduct.getProductId())
                .name(domainProduct.getName())
                .host(Optional.ofNullable(domainProduct.getHostId())
                                .map(HostDTO::fromDomainHost)
                                .orElse(null))
                .images(domainProduct.getImages())
                .bathrooms(domainProduct.getBathrooms())
                .bedrooms(domainProduct.getBedrooms())
                .description(domainProduct.getDescription())
                .beds(domainProduct.getBeds())
                .attributes(domainProduct.getAttributes())
                .rentedDays(domainProduct.getRentedDays())
                .reviews(domainProduct.getReviews())
                .build();
        product.setPrice(domainProduct.getPrice());
        product.setAddress(domainProduct.getAddress());
        return product;
    }
}
