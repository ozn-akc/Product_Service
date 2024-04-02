package de.seven.product.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
public class Product {
    String productId;
    String name;
    String hostId;
    List<String> images;
    Integer bedrooms;
    Integer bathrooms;
    String description;
    Price price;
    Address address;
    List<Bed> beds;
    List<Attribute> attributes;
    List<Date> rentedDays;
    List<Review> reviews;
}
