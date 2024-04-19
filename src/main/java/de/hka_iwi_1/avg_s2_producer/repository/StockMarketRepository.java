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

import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static de.hka_iwi_1.avg_s2_producer.repository.DB.STOCK_MARKET;
import static java.util.Collections.emptyList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for handling the database connection.
 */
@Repository
@Slf4j
public class StockMarketRepository {

    /**
     * Find stock marktes by id.
     *
     * @param id The id of the stock market.
     * @return The stock market or an empty optional object.
     */
    public Optional<StockMarket> findById(final UUID id) {
        log.debug("findById: id={}", id);
        final var result = STOCK_MARKET.stream()
                .filter(stockMarket -> Objects.equals(stockMarket.getId(), id))
                .findFirst();
        log.debug("findById: {}", result);
        return result;
    }

    /**
     * Find all stocks.
     *
     * @return List containing all stocks.
     */
    public @NonNull Collection<StockMarket> findAll() {
        return STOCK_MARKET;
    }

    /**
     * Find a stock market by name
     *
     * @param name The name of the stock market.
     * @return Collection containing all found stock markets.
     */
    public @NonNull Collection<StockMarket> findByName(final CharSequence name) {
        log.debug("findByNachname: name={}", name);
        final var stockmarkets = STOCK_MARKET.stream()
                .filter(stockmarket -> stockmarket.getName().contains(name))
                .toList();
        log.debug("findByName: {}", stockmarkets);
        return stockmarkets;
    }

    /**
     * Find stock markets based on search criterias.
     *
     * @param suchkriterien The search criterias.
     * @return Collection containing all found stock markets.
     */
    public @NonNull Collection<StockMarket> find(final Map<String, List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);
        if (suchkriterien.isEmpty()) {
            return findAll();
        }

        if (suchkriterien.size() > 1) {
            log.debug("find: Es darf nur ein Suchkriterium angegeben werden");
            return emptyList();
        }

        if (suchkriterien.containsKey("name")) {
            String name = suchkriterien.get("name").get(0);
            return findByName(suchkriterien.get("name").get(0));
        }
        return emptyList();
    }
}
