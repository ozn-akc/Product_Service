package de.seven.product.adapter.secondary.postgresql.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity(name = "Product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;
    String name;
    String hostId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    List<Image> images;
    Integer bedrooms;
    Integer bathrooms;
    String description;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Price price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    List<Bed> beds;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    List<Attribute> attributes;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    List<RentedDay> rentedDays;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "productId")
    List<Review> reviews;

    public de.seven.product.domain.model.Product toDomainProduct() {
        return de.seven.product.domain.model.Product.builder()
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
    }

    public static Product fromDomainProduct(de.seven.product.domain.model.Product domainProduct) {
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
