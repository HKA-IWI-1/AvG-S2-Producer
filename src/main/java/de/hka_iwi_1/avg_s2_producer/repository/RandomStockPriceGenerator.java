/*
 * Copyright (c) 2024 - present Ronny Friedmann
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

import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.hka_iwi_1.avg_s2_producer.repository.DB.STOCK_MARKET;

/**
 * Class for generating random stock data.
 */
public class RandomStockPriceGenerator {

    private BigDecimal currentPrice;
    private BigDecimal newPrice;

    private BigDecimal generateRandomPrice(BigDecimal price) {

        Random random = new Random();
        short addOrSubtract;

        if (random.nextBoolean()) {
            addOrSubtract = 1;
        } else {
            addOrSubtract = -1;
        }
        double trend = (random.nextDouble(1, 5) * addOrSubtract);

        price = price.add(BigDecimal.valueOf(trend));
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            trend = random.nextDouble(0, 1);
            price = price.add(BigDecimal.valueOf(trend));
        }
        return price;
    }

    /**
     * Method for generating stock data.
     */
    @Scheduled(fixedRate = 5000)
    public void generateStockPrice() {
        STOCK_MARKET
                .forEach(market -> market.getShares()
                        .forEach(share -> {
                            List<BigDecimal> stockHistory = new ArrayList<>(share.getPriceHistory());
                            currentPrice = stockHistory.getLast();
                            if (stockHistory.size() >= 10) stockHistory.removeFirst();
                            newPrice = generateRandomPrice(currentPrice);
                            stockHistory.add(newPrice);
                            share.setPriceHistory(stockHistory);
                        }));

    }
}
