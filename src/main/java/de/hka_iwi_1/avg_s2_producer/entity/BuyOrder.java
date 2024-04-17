package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public abstract class BuyOrder extends AbstractOrder {

    protected BigDecimal maxPrice;

}
