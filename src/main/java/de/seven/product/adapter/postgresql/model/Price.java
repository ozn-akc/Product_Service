package de.seven.product.adapter.postgresql.model;

import de.seven.product.domain.model.Unit;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="Price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private Product product;
    Double value;
    Unit unit;

    public de.seven.product.domain.model.Price toDomainPrice(){
        de.seven.product.domain.model.Price price = de.seven.product.domain.model.Price.builder()
                .unit(unit)
                .value(value)
                .build();
        return price;
    }

    public static Price fromDomainPrice(de.seven.product.domain.model.Price domainPrice, Product product){
        Price price = Price.builder()
                .unit(domainPrice.getUnit())
                .value(domainPrice.getValue())
                .product(product)
                .build();
        return price;
    }
}
