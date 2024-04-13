package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Share {

    // Regex: ^\b[A-NP-Z0-9]{6}\b$
    private String wkn;

    private int availableShares;

    private BigDecimal price;

    private Collection<BigDecimal> prices24hrs;

}
