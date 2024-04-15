package de.hka_iwi_1.avg_s2_producer.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStockPriceGenerator {

    private List<BigDecimal> stockHistory = new ArrayList<>();
    private BigDecimal currentPrice = BigDecimal.valueOf(100); // Startpreis 100
    private Random random = new Random();


    private BigDecimal generateRandomPrice() {
        double trend = random.nextGaussian() * 5; // Standardabweichung von 5
        currentPrice = currentPrice.add(BigDecimal.valueOf(trend));

        // Mindestwert von 0; falls nicht 0 setzung
        if (currentPrice.compareTo(BigDecimal.ZERO) < 0) {
            currentPrice = BigDecimal.ZERO;
        }

        return currentPrice;
    }

    // Alle 10 Sekunden ausführen (10.000)
    @Scheduled(fixedRate = 10000)
    public void generateStockPrice() {
        BigDecimal newPrice = generateRandomPrice();
        stockHistory.add(newPrice);

        //Max. 10 Elemente
        if (stockHistory.size() > 10) {
            stockHistory.removeFirst();
        }
    }
}
