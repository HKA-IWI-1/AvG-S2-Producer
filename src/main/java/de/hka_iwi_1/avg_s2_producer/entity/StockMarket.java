package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * A stock market trading with shares.
 */
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StockMarket implements Serializable {

    /**
     * The id of the order.
     */
    private UUID id;

    /**
     * The name of the stock market.
     */
    private String name;

    /**
     * Collection containing the shared traded by the stock market.
     */
    private Collection<Share> shares;
}
