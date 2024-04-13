package de.hka_iwi_1.avg_s2_producer.repository;

import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import static de.hka_iwi_1.avg_s2_producer.repository.DB.STOCK_MARKET;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class StockMarketRepository {

    public Optional<StockMarket> findById(final UUID id) {
        log.debug("findById: id={}", id);
        final var result = STOCK_MARKET.stream()
                .filter(stockMarket -> Objects.equals(stockMarket.getId(), id))
                .findFirst();
        log.debug("findById: {}", result);
        return result;
    }

    public @NonNull Collection<StockMarket> findByName(final CharSequence name) {
        log.debug("findByNachname: name={}", name);
        final var stockmarkets = STOCK_MARKET.stream()
                .filter(stockmarket -> stockmarket.getName().contains(name))
                .toList();
        log.debug("findByName: {}", stockmarkets);
        return stockmarkets;
    }

}
