package org.example.naturalidsample.product;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@ToString
@ConfigurationProperties("natural-id-sample.discount-v2")
public class DiscountPropertiesV2 {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<String> keys;

    public DiscountPropertiesV2(LocalDate startDate, LocalDate endDate, Set<String> keys) {
        this.startDate = Objects.requireNonNullElse(startDate, LocalDate.MAX);
        this.endDate = Objects.requireNonNullElse(endDate, LocalDate.MIN);
        this.keys = Objects.requireNonNullElse(keys, Set.of());
    }

    public boolean isDiscountTarget(String key, LocalDate now) {
        if (now.isBefore(startDate)) {
            return false;
        }
        if (now.isAfter(endDate)) {
            return false;
        }
        return keys.contains(key);
    }

}
