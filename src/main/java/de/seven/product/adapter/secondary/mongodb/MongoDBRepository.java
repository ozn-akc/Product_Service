package de.seven.product.adapter.secondary.mongodb;

import de.seven.product.adapter.secondary.mongodb.model.HostDTO;
import de.seven.product.adapter.secondary.mongodb.model.ProductDTO;
import de.seven.product.application.adapter.secondary.ProductRepository;
import de.seven.product.domain.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("mongodb")
@RequiredArgsConstructor
public class MongoDBRepository implements ProductRepository {

    private final ProductMongoRepository productMongoRepository;
    private final HostMongoRepository hostMongoRepository;

    @Override
    @Transactional
    public Product save(Product domainProduct) {
        ProductDTO product = ProductDTO.fromDomainProduct(domainProduct);
        hostMongoRepository.findById(domainProduct.getHostId()).orElseThrow();
        productMongoRepository.save(product);
        return product.toDomainProduct();
    }

    @Override
    public String saveHost(String domainHost) {
        HostDTO host = HostDTO.fromDomainHost(domainHost);
        hostMongoRepository.save(host);
        return host.toDomainHost();
    }

    @Override
    public Product findById(String productId) {
        return productMongoRepository.findById(productId).orElse(ProductDTO.builder().build()).toDomainProduct();
    }

    @Override
    public List<Product> findAll() {
        return productMongoRepository.findAll().stream().map(ProductDTO::toDomainProduct).toList();
    }

    @Override
    public void delete(String productId) {
        productMongoRepository.deleteById(productId);
    }

    @Override
    public void deleteHost(String hostId) {
        hostMongoRepository.deleteById(hostId);

    }
}
