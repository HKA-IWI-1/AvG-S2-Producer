package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BuyOrder extends AbstractOrder {

    private BigDecimal maxPrice;

}
