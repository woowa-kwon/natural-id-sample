package org.example.naturalidsample.product;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@ConfigurationProperties("natural-id-sample.discount")
public class DiscountProperties {

    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Long> ids;

    public boolean isDiscountTarget(Long id, LocalDate now) {
        if (now.isBefore(startDate)) {
            return false;
        }
        if (now.isAfter(endDate)) {
            return false;
        }
        return ids.contains(id);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = Objects.requireNonNullElse(startDate, LocalDate.MAX);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = Objects.requireNonNullElse(endDate, LocalDate.MIN);
    }

    public void setIds(Set<Long> ids) {
        this.ids = Objects.requireNonNullElse(ids, Set.of());
    }

}
