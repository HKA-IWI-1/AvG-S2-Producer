package de.hka_iwi_1.avg_s2_producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;
import de.hka_iwi_1.avg_s2_producer.repository.StockMarketRepository;
import de.hka_iwi_1.avg_s2_producer.websocket.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class SendService {

    private final StockMarketRepository repo;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;


    @Value("${jms.SendPricesTest}")
    String jmsQueue;

    public void sendPrices(Map<String, List<String>> suchkriterien) throws JsonProcessingException {
        Collection<StockMarket> stockMarkets = repo.find(suchkriterien); // Annahme: repo ist dein Repository

        if (stockMarkets.isEmpty()) {
            throw new NotFoundException(suchkriterien.toString());
        }

        // Konvertiere StockMarket Collection in einen JSON-String
        String jsonMessage = objectMapper.writeValueAsString(stockMarkets);

        // Sende den JSON-String
        jmsTemplate.convertAndSend(jmsQueue, jsonMessage);
    }

}
