package de.hka_iwi_1.avg_s2_producer.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StockMarket implements Serializable {

    private UUID id;

    private String name;

    private List<Share> shares;
}
