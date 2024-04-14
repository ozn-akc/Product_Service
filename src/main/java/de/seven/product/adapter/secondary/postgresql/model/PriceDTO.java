package de.seven.product.adapter.secondary.postgresql.model;

import de.seven.product.domain.model.Price;
import de.seven.product.domain.model.Unit;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "productId")
    @MapsId
    private ProductDTO product;
    Double value;
    Unit unit;

    public Price toDomainPrice() {
        Price price = Price.builder()
                .unit(unit)
                .value(value)
                .build();
        return price;
    }

    public static PriceDTO fromDomainPrice(Price domainPrice, ProductDTO product) {
        PriceDTO price = PriceDTO.builder()
                .unit(domainPrice.getUnit())
                .value(domainPrice.getValue())
                .product(product)
                .build();
        return price;
    }
}
