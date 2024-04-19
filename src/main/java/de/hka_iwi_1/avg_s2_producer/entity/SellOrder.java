package de.hka_iwi_1.avg_s2_producer.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * The sell order.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SellOrder extends AbstractOrder {

    /**
     * The minimum price at which the order should be executed.
     */
    private BigDecimal minPrice;
}
