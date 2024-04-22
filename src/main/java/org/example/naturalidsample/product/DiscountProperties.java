package org.example.naturalidsample.product;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@ToString
@ConfigurationProperties("natural-id-sample.discount")
public class DiscountProperties {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Set<Long> ids;

    public DiscountProperties(LocalDate startDate, LocalDate endDate, Set<Long> ids) {
        this.startDate = Objects.requireNonNullElse(startDate, LocalDate.MAX);
        this.endDate = Objects.requireNonNullElse(endDate, LocalDate.MIN);
        this.ids = Objects.requireNonNullElse(ids, Set.of());
    }

    public boolean isDiscountTarget(Long id, LocalDate now) {
        if (now.isBefore(startDate)) {
            return false;
        }
        if (now.isAfter(endDate)) {
            return false;
        }
        return ids.contains(id);
    }

}
