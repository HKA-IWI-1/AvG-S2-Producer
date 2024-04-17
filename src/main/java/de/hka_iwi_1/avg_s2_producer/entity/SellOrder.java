package de.hka_iwi_1.avg_s2_producer.entity;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.UUID;

public class SellOrder extends AbstractOrder {

    protected BigDecimal minPrice;
}
