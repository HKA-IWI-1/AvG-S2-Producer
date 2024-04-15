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
                                        .priceHistory(Stream.of(
                                                        new BigDecimal("124.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("124.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("134.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("34.03"),
                                                        new BigDecimal("54.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("19.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("134.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("34.03"),
                                                        new BigDecimal("54.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("19.03"),
                                                        new BigDecimal("124.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("124.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("12.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("26.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("124.03"),
                                                        new BigDecimal("24.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("134.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("34.03"),
                                                        new BigDecimal("54.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("19.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("14.03"),
                                                        new BigDecimal("134.03"),
                                                        new BigDecimal("84.03"),
                                                        new BigDecimal("34.03")
                                                ).collect(Collectors.toList())
                                        )
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
                                        .priceHistory(Stream.of(
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("12.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("26.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("26.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("12.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("26.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("17.03"),
                                                        new BigDecimal("70.03"),
                                                        new BigDecimal("12.03"),
                                                        new BigDecimal("83.03"),
                                                        new BigDecimal("26.03"),
                                                        new BigDecimal("90.89"),
                                                        new BigDecimal("96.03"),
                                                        new BigDecimal("15.03"),
                                                        new BigDecimal("69.03"),
                                                        new BigDecimal("75.03"),
                                                        new BigDecimal("10.03")
                                                ).collect(Collectors.toList())
                                        )
                                        .wkn("234567")
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()
        ).collect(Collectors.toList());
    }

    private DB() {
    }
}
