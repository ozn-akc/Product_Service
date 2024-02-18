package de.seven.product.domain.model;

import java.util.Date;
import java.util.List;

public class Product {
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
