package de.seven.product.adapter.secondary.mongodb;

import de.seven.product.adapter.secondary.mongodb.model.HostDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HostMongoRepository extends MongoRepository<HostDTO, String> {
}
