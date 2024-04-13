package de.hka_iwi_1.avg_s2_producer.repository;

import de.hka_iwi_1.avg_s2_producer.entity.Share;
import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DB {

static final List<StockMarket> STOCK_MARKET;

static {
    STOCK_MARKET = Stream.of(
            StockMarket.builder()
                    .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                    .name("Stuttgart")
                    .shares(Stream.of(
                            Share.builder()
                                    .availableShares(100)
                                    .price(new BigDecimal("124.09"))
                                    .prices24hrs(Arrays.asList(new BigDecimal("124.03"), new BigDecimal("123.99")))
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
                                    .price(new BigDecimal("384.83"))
                                    .prices24hrs(Arrays.asList(new BigDecimal("384.85"), new BigDecimal("384.95")))
                                    .wkn("234567")
                                    .build()
                    ).collect(Collectors.toList()))
                    .build()
    ).collect(Collectors.toList());
}

    private DB() {
    }
}
