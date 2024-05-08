package de.seven.product.adapter.secondary.mongodb.model;

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
    String id;

    public String toDomainHost() {
        return id;
    }

    public static HostDTO fromDomainHost(String hostId) {
        return HostDTO.builder()
                .id(hostId)
                .build();
    }
}
