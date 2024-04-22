package org.example.naturalidsample.product;

import java.util.Optional;

public interface ProductWithNaturalIdRepositoryCustom {

    Optional<ProductWithNaturalId> findByNaturalId(String key);

}
