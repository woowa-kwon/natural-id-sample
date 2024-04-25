package org.example.naturalidsample.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private static final double DISCOUNT_RATE = 0.8;

    private final ProductRepository productRepository;
    private final DiscountProperties discountProperties;

    public ProductService(ProductRepository productRepository, DiscountProperties discountProperties) {
        this.productRepository = productRepository;
        this.discountProperties = discountProperties;
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
        if (!discountProperties.isDiscountTarget(product.getId(), now)) {
            return price;

        }
        return price - (int) (price * DISCOUNT_RATE);
    }

}
