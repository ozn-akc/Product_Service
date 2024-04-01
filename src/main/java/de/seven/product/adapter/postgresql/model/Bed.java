package de.seven.product.adapter.postgresql.model;

import de.seven.product.domain.model.BedType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name="Bed")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bed {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String productId;
    Integer amount;
    BedType type;

    public de.seven.product.domain.model.Bed toDomainBed(){
        de.seven.product.domain.model.Bed bed = de.seven.product.domain.model.Bed.builder()
                .amount(amount)
                .type(type)
                .build();
        return bed;
    }

    public static Bed fromDomainBed(de.seven.product.domain.model.Bed domainBed){
        Bed bed = Bed.builder()
                .amount(domainBed.getAmount())
                .type(domainBed.getType())
                .build();
        return bed;
    }
}
