package de.seven.product.adapter.postgresql.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity(name="Product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String productId;
    String name;
    String hostId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId")
    List<Image> images;
    Integer bedrooms;
    Integer bathrooms;
    String description;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Price price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId")
    List<Bed> beds;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId")
    List<Attribute> attributes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId")
    List<RentedDay> rentedDays;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="productId")
    List<Review> reviews;

    public de.seven.product.domain.model.Product toDomainProduct(){
        de.seven.product.domain.model.Product product = de.seven.product.domain.model.Product.builder()
                .productId(productId)
                .name(name)
                .hostId(hostId)
                .images(Optional.ofNullable(images)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(image -> image.url)
                        .toList())
                .bedrooms(bedrooms)
                .bathrooms(bathrooms)
                .description(description)
                .price(Optional.ofNullable(price)
                        .map(Price::toDomainPrice)
                        .orElse(null))
                .beds(Optional.ofNullable(beds)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Bed::toDomainBed)
                        .toList())
                .attributes(Optional.ofNullable(attributes)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(attribute -> attribute.attribute)
                        .toList())
                .rentedDays(Optional.ofNullable(rentedDays)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(rentedDay -> rentedDay.day)
                        .toList())
                .reviews(Optional.ofNullable(reviews)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Review::toDomainReview)
                        .toList())
                .build();
        return product;
    }

    public static Product fromDomainProduct(de.seven.product.domain.model.Product domainProduct){
        Product product = Product.builder()
                .productId(domainProduct.getProductId())
                .name(domainProduct.getName())
                .hostId(domainProduct.getHostId())
                .images(
                        Optional.ofNullable(domainProduct.getImages())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(image -> Image.builder().url(image).build())
                        .toList()
                )
                .bathrooms(domainProduct.getBathrooms())
                .bedrooms(domainProduct.getBedrooms())
                .description(domainProduct.getDescription())
                .beds(
                        Optional.ofNullable(domainProduct.getBeds())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Bed::fromDomainBed)
                        .toList()
                )
                .attributes(
                        Optional.ofNullable(domainProduct.getAttributes())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(attribute -> Attribute.builder().attribute(attribute).build())
                                .toList()
                )
                .rentedDays(
                        Optional.ofNullable(domainProduct.getRentedDays())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(rentedDay -> RentedDay.builder().day(rentedDay).build())
                        .toList()
                )
                .reviews(
                        Optional.ofNullable(domainProduct.getReviews())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Review::fromDomainReview)
                        .toList()
                )
                .build();
        product.setPrice(Optional.ofNullable(domainProduct.getPrice())
                .map(price -> Price.fromDomainPrice(price, product))
                .orElse(null));
        product.setAddress(Optional.ofNullable(domainProduct.getAddress())
                .map(address -> Address.fromDomainAddress(address, product))
                .orElse(null));
        return product;
    }
}
