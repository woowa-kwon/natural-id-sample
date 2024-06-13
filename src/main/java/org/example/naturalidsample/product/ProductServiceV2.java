package org.example.naturalidsample.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceV2 {

    private static final Set<String> DISCOUNT_TARGET_KEYS = Set.of("PRODUCT_AAA","PRODUCT_CCC","PRODUCT_EEE","PRODUCT_XXX");

    private static final double DISCOUNT_RATE = 0.8;

    private final ProductRepository productRepository;
    private final DiscountPropertiesV2 discountPropertiesV2;

    public ProductServiceV2(ProductRepository productRepository, DiscountPropertiesV2 discountPropertiesV2) {
        this.productRepository = productRepository;
        this.discountPropertiesV2 = discountPropertiesV2;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        final LocalDate now = LocalDate.now();
        return productRepository.findAll().stream()
                .map(product -> convertToDto(product, now))
                .toList();
    }

    private ProductDto convertToDto(Product product, LocalDate now) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(getPrice(product, now))
                .build();
    }

    private int getPrice(Product product, LocalDate now) {
        final int price = product.getPrice();
        if (!discountPropertiesV2.isDiscountTarget(product.getKey(), now)) {
            return price;

        }
        return price - (int) (price * DISCOUNT_RATE);
    }

}
