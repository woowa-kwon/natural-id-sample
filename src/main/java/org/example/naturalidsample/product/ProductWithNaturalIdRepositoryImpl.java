package org.example.naturalidsample.product;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ProductWithNaturalIdRepositoryImpl implements ProductWithNaturalIdRepositoryCustom {

    private final EntityManager entityManager;

    public ProductWithNaturalIdRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<ProductWithNaturalId> findByNaturalId(String key) {
        return entityManager.unwrap(Session.class)
                .bySimpleNaturalId(ProductWithNaturalId.class)
                .loadOptional(key);
    }

}
