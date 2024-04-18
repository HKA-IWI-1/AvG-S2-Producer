package de.hka_iwi_1.avg_s2_producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hka_iwi_1.avg_s2_producer.entity.*;
import de.hka_iwi_1.avg_s2_producer.repository.StockMarketRepository;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
@Component
public class ReceiveService {

    private final ObjectMapper mapper;
    private final StockMarketRepository repository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${jms.stocks.newOrder.Frankfurt}")
    @JmsListener(destination = "${jms.stocks.newOrder.Stuttgart}")
    public void receiveOrder(Message message) throws JsonProcessingException, JMSException, InterruptedException {
        log.debug("orderString: " + message.getBody(String.class));
        var orderWrapper = mapper.readValue(message.getBody(String.class), OrderWrapper.class);
        byte sellOrBuy;
        log.debug("orderWrapper" + orderWrapper.toString());
        AbstractOrder order = orderWrapper.getBuyOrder() != null ? orderWrapper.getBuyOrder() : orderWrapper.getSellOrder();
        log.debug("order: " + order.toString());
        if(order == orderWrapper.getBuyOrder())
            sellOrBuy = -1;
        else
            sellOrBuy = 1;
        Collection<StockMarket> stockMarkets = repository.findAll();

        int index = message.getJMSDestination().toString().lastIndexOf(".") + 1;
        int index2 = message.getJMSDestination().toString().lastIndexOf("]");
        String market = message.getJMSDestination().toString().substring(index, index2);

        List<Share> shareList = stockMarkets.stream()
                .filter(stockMarket -> stockMarket.getName().equals(market))
                .flatMap(stockMarket -> stockMarket.getShares().stream())
                .filter(share -> share.getWkn().equals(order.getWkn()))
                .toList();
        Share share = shareList.getFirst();

        if(share.getAvailableShares()-order.getAmount() < 0 && orderWrapper.getBuyOrder() != null){
            order.setStatus(OrderStatusType.ERROR);
        }
        else{
            share.setAvailableShares(share.getAvailableShares() + (order.getAmount()*sellOrBuy));
            order.setStatus(OrderStatusType.SUCCESS);
        }

        String jsonMessage = mapper.writeValueAsString(orderWrapper);

        TimeUnit.SECONDS.sleep(5);
        jmsTemplate.setPubSubDomain(false);
        jmsTemplate.convertAndSend(statusQueueBuilder(message.getJMSDestination().toString(),
                order.getClientId()), jsonMessage);
    }

    private String statusQueueBuilder(String queue, int clientId) {
        StringBuilder newQueue = new StringBuilder("stocks.c");
        newQueue.append(clientId);
        newQueue.append(".orderStatus.");
        int index = queue.lastIndexOf(".") + 1;
        int index2 = queue.lastIndexOf("]");
        newQueue.append(queue, index, index2);
        return newQueue.toString();
    }

}
