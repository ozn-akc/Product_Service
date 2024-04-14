package de.seven.product.adapter.secondary.postgresql.model;

import de.seven.product.domain.model.Attribute;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Attribute")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String productId;
    Attribute attribute;
}
