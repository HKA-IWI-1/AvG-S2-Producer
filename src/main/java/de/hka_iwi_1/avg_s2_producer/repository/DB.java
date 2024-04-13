/*
 * Copyright (c) 2023-2024 - present Florian Sauer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the “Software”), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package de.hka_iwi_1.avg_s2_producer.repository;

import de.hka_iwi_1.avg_s2_producer.entity.Share;
import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
                                    .prices24hrs()
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
                                    .prices24hrs()
                                    .wkn("234567")
                                    .build()
                    ).collect(Collectors.toList()))
                    .build()
    ).collect(Collectors.toList());
}

    private DB() {
    }
}
