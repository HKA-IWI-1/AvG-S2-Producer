package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * A share that can be bought or sold.
 */
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Share implements Serializable {

    /**
     * The WKN (Wertpapierkennnummer) of the share. Acts as the id.
     * Regex: ^\b[A-NP-Z0-9]{6}\b$
     */
    private String wkn;

    /**
     * The amount of available shares.
     */
    private int availableShares;

    // todo: handle prices.

    /**
     * Collection containing a price history. E.g. used for graphs.
     */
    private List<BigDecimal> priceHistory;
}
