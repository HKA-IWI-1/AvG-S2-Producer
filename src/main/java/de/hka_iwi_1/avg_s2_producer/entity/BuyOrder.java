package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.*;

import java.math.BigDecimal;

/**
 * The buy order.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrder extends AbstractOrder {

    /**
     * The maximum price at which the order should be executed.
     */
    private BigDecimal maxPrice;

}
