package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Share implements Serializable {

    // Regex: ^\b[A-NP-Z0-9]{6}\b$
    private String wkn;

    private int availableShares;

    private List<BigDecimal> priceHistory;

}
