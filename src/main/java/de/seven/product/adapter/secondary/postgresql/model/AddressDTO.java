package de.seven.product.adapter.secondary.postgresql.model;

import de.seven.product.domain.model.Address;
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
public class AddressDTO {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private ProductDTO product;
    String street;
    String number;
    String country;
    String city;

    public Address toDomainAddress() {
        Address address = Address.builder()
                .city(city)
                .country(country)
                .number(number)
                .street(street)
                .build();
        return address;
    }

    public static AddressDTO fromDomainAddress(Address domainAddress, ProductDTO product) {
        AddressDTO address = AddressDTO.builder()
                .city(domainAddress.getCity())
                .country(domainAddress.getCountry())
                .number(domainAddress.getNumber())
                .street(domainAddress.getStreet())
                .product(product)
                .build();
        return address;
    }
}
