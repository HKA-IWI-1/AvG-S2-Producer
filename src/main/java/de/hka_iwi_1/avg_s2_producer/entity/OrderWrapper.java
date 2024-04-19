package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.*;

/**
 * Wrapper for buy/sell orders.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderWrapper {

    BuyOrder buyOrder;
    SellOrder sellOrder;
}
