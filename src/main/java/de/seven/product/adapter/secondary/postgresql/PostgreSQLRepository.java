package de.seven.product.adapter.secondary.postgresql;

import de.seven.product.adapter.secondary.postgresql.model.Product;
import de.seven.product.application.adapter.secondary.ProductRepository;
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
    public de.seven.product.domain.model.Product save(de.seven.product.domain.model.Product product) {
        Product data = Product.fromDomainProduct(product);
        entityManager.persist(data);
        entityManager.flush();
        return data.toDomainProduct();
    }

    @Override
    public de.seven.product.domain.model.Product findById(String productId) {
        return Optional.ofNullable(entityManager.find(Product.class, productId)).orElse(Product.builder().build()).toDomainProduct();
    }

    @Override
    public List<de.seven.product.domain.model.Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList().stream().map(Product::toDomainProduct).toList();
    }

    @Override
    public void delete(String productId) {
        entityManager.remove(entityManager.find(Product.class, productId));
    }
}

