package de.hka_iwi_1.avg_s2_producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hka_iwi_1.avg_s2_producer.entity.*;
import de.hka_iwi_1.avg_s2_producer.repository.StockMarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Component
public class ReceiveService {

    private final ObjectMapper mapper;
    private final StockMarketRepository repository;
    private final JmsTemplate jmsTemplate;

    @Value("${jms.stocks.c1.orderStatus.Stuttgart}")
    String c1QueueStuttgart;

    @Value("${jms.stocks.c1.orderStatus.Frankfurt}")
    String c1QueueFrankfurt;

    @Value("${jms.stocks.c2.orderStatus.Stuttgart}")
    String c2QueueStuttgart;

    @Value("${jms.stocks.c2.orderStatus.Frankfurt}")
    String c2QueueFrankfurt;

    @JmsListener(destination = "${jms.stocks.newOrder.Frankfurt}")
    @JmsListener(destination = "${jms.stocks.newOrder.Stuttgart}")
    public void receiveOrder(String orderString, @Header("JMSDestination") String JMSDestination) throws JsonProcessingException {
        var orderWrapper = mapper.readValue(orderString, OrderWrapper.class);
        byte sellOrBuy;

        AbstractOrder order = orderWrapper.getBuyOrder() != null ? orderWrapper.getBuyOrder() : orderWrapper.getSellOrder();
        if(order == orderWrapper.getBuyOrder())
            sellOrBuy = -1;
        else
            sellOrBuy = 1;
        Collection<StockMarket> stockMarkets = repository.findAll();

        int index = JMSDestination.lastIndexOf(".") + 1;
        String market = JMSDestination.substring(index);

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

        String jsonMessage = mapper.writeValueAsString(order);
        String newQueue = statusQueueBuilder(JMSDestination, order.getClientId());
        jmsTemplate.convertAndSend(newQueue, jsonMessage);
    }

    private String statusQueueBuilder(String queue, int clientId) {
        StringBuilder newQueue = new StringBuilder("stocks.");
        newQueue.append(clientId);
        newQueue.append(".orderStatus.");
        int index = queue.lastIndexOf(".") + 1;
        newQueue.append(queue.substring(index));

        return newQueue.toString();
    }

}
