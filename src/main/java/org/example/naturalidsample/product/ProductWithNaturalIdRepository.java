package org.example.naturalidsample.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductWithNaturalIdRepository extends JpaRepository<ProductWithNaturalId, Long>, ProductWithNaturalIdRepositoryCustom {

    Optional<ProductWithNaturalId> findByKey(String key);

}
