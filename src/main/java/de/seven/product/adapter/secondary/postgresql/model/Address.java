package de.seven.product.adapter.secondary.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private Product product;
    String street;
    String number;
    String country;
    String city;

    public de.seven.product.domain.model.Address toDomainAddress() {
        de.seven.product.domain.model.Address address = de.seven.product.domain.model.Address.builder()
                .city(city)
                .country(country)
                .number(number)
                .street(street)
                .build();
        return address;
    }

    public static Address fromDomainAddress(de.seven.product.domain.model.Address domainAddress, Product product) {
        Address address = Address.builder()
                .city(domainAddress.getCity())
                .country(domainAddress.getCountry())
                .number(domainAddress.getNumber())
                .street(domainAddress.getStreet())
                .product(product)
                .build();
        return address;
    }
}
