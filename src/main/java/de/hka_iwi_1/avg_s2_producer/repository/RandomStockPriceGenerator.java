package de.hka_iwi_1.avg_s2_producer.repository;

import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.hka_iwi_1.avg_s2_producer.repository.DB.STOCK_MARKET;

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

    // Alle 10 Sekunden ausführen (10.000)
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
