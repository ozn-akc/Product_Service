package de.seven.product.adapter.secondary.mongodb.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hosts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HostDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String hostId;

    public String toDomainHost() {
        return hostId;
    }

    public static HostDTO fromDomainHost(String hostId) {
        return HostDTO.builder()
                .hostId(hostId)
                .build();
    }
}
