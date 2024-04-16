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

        double trend = random.nextGaussian() * 5; // Standardabweichung von 5
        price = price.add(BigDecimal.valueOf(trend));

        // Mindestwert von 0; falls nicht 0 setzung
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            price = BigDecimal.ZERO;
        }

        return price;

        /* Vorschlag für nen Pricegenerator der immer kleine Werte ändert aber nicht in nem Bereich festbleibt
        Double decimal = 20.0;
        short addOrSubtract;
        double roundedNumber;
        while(true) {
            if (random.nextBoolean()){
                System.out.println("positiv");
            addOrSubtract = 1;
            }else{
                addOrSubtract = -1;
                System.out.println("negativ");
            }
            decimal += (random.nextDouble(1,5) * addOrSubtract);
            if(decimal <= 0.0)
                decimal = random.nextDouble(0,1);
            roundedNumber = Math.round(decimal * 100) / 100.0;
            System.out.println(roundedNumber);
        }*/
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
