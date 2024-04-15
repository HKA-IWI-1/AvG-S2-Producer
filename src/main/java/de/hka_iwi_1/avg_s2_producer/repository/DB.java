package de.hka_iwi_1.avg_s2_producer.repository;

import de.hka_iwi_1.avg_s2_producer.entity.Share;
import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DB {

static List<StockMarket> STOCK_MARKET;

static {
    STOCK_MARKET = Stream.of(
            StockMarket.builder()
                    .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                    .name("Stuttgart")
                    .shares(Stream.of(
                            Share.builder()
                                    .availableShares(100)
                                    // TODO: Preise generieren lassen
                                    .priceHistory(List.of(new BigDecimal("124.03")))
                                    .wkn("123456")
                                    .build()
                    ).collect(Collectors.toList()))
                    .build(),
            StockMarket.builder()
                    .id(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                    .name("Frankfurt")
                    .shares(Stream.of(
                            Share.builder()
                                    .availableShares(200)
                                    // TODO: Preise generieren lassen
                                    .priceHistory(List.of(new BigDecimal("90.89")))
                                    .wkn("234567")
                                    .build()
                    ).collect(Collectors.toList()))
                    .build()
    ).collect(Collectors.toList());
}

    private DB() {
    }
}
