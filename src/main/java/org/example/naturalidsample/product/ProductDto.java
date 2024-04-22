package org.example.naturalidsample.product;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductDto {

    private final Long id;
    private final String name;
    private final int price;

    @Builder
    public ProductDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
