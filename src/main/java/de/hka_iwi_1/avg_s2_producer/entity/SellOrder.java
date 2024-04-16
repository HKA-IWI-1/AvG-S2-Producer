package de.hka_iwi_1.avg_s2_producer.entity;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.UUID;

public class SellOrder {

    // created by client
    private UUID id;

    @Value("${client.id}")
    private int clientId;

    private UUID exchangeId;

    // Regex: ^\b[A-NP-Z0-9]{6}\b$
    private String wkn;

    private int amount;

    //protected OrderStatusType status;

    private BigDecimal minPrice;
}
