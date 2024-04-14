package de.seven.product.adapter.secondary.postgresql;

import de.seven.product.adapter.secondary.postgresql.model.ProductDTO;
import de.seven.product.application.adapter.secondary.ProductRepository;
import de.seven.product.domain.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("postgresql")
@RequiredArgsConstructor
public class PostgreSQLRepository implements ProductRepository {

    private final EntityManager entityManager;

    @Override
    public Product save(Product product) {
        ProductDTO data = ProductDTO.fromDomainProduct(product);
        entityManager.persist(data);
        entityManager.flush();
        return data.toDomainProduct();
    }

    @Override
    public Product findById(String productId) {
        return Optional.ofNullable(entityManager.find(ProductDTO.class, productId)).orElse(ProductDTO.builder().build()).toDomainProduct();
    }

    @Override
    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<ProductDTO> query = entityManager.createQuery(jpql, ProductDTO.class);
        return query.getResultList().stream().map(ProductDTO::toDomainProduct).toList();
    }

    @Override
    public void delete(String productId) {
        entityManager.remove(entityManager.find(ProductDTO.class, productId));
    }
}

