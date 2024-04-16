package de.hka_iwi_1.avg_s2_producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hka_iwi_1.avg_s2_producer.entity.BuyOrder;
import de.hka_iwi_1.avg_s2_producer.entity.OrderWrapper;
import de.hka_iwi_1.avg_s2_producer.entity.Share;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
@Component
public class ReceiveService {
    private final ObjectMapper mapper;
    @JmsListener(destination = "${jms.stocks.newOrder.#}")
    public void receiveMessage(String orderString) throws JsonProcessingException {
        var orderxd = mapper.readValue(orderString, OrderWrapper.class);
        if (orderxd.getBuyOrder() != null){

        }
        else {

        }

    }

}
