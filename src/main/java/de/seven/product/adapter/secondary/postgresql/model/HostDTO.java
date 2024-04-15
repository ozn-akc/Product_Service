package de.seven.product.adapter.secondary.postgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "Host")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HostDTO {
    @Id
    String hostId;

    public String toDomainHost() {
        return hostId;
    }

    public static HostDTO fromDomainHost(String hostId) {
        HostDTO host = HostDTO.builder()
                .hostId(hostId)
                .build();
        return host;
    }
}
