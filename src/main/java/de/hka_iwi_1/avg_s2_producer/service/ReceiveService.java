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
    //todo: alles beenden

    @JmsListener(destination = "${jms.stocks.newOrder.Frankfurt}")
    public void receiveOrderFrankfurt(String orderString) throws JsonProcessingException {
        var orderxd = mapper.readValue(orderString, OrderWrapper.class);
        byte sellOrBuy;

        AbstractOrder order = orderxd.getBuyOrder() != null ? orderxd.getBuyOrder() : orderxd.getSellOrder();
        if(order == orderxd.getBuyOrder())
            sellOrBuy = -1;
        else
            sellOrBuy = 1;
        Collection<StockMarket> stockMarkets = repository.findAll();

        List<Share> shareList = stockMarkets.stream()
                .filter(stockMarket -> stockMarket.getName().equals("Frankfurt"))
                .flatMap(stockMarket -> stockMarket.getShares().stream())
                .filter(share -> share.getWkn().equals(order.getWkn()))
                .toList();
        Share share = shareList.getFirst();
        /*if(share.getAvailableShares()-order.getAmount() < 0){
            throw new Exception();
        } optional*/
        share.setAvailableShares(share.getAvailableShares() + (order.getAmount()*sellOrBuy));

        order.setStatus(OrderStatusType.SUCCESS);
        String jsonMessage = mapper.writeValueAsString(order);
        if (order.getClientId() == 1){
            jmsTemplate.convertAndSend(c1QueueFrankfurt, jsonMessage);
        }



        /*if (orderxd.getBuyOrder() != null) {
            Collection<StockMarket> stockMarkets = repository.findAll();
            stockMarkets.forEach(stockMarket -> {
                if ("Frankfurt".equals(stockMarket.getName()))
                    stockMarket.getShares()
                            .forEach(share -> {

                                share.setAvailableShares(share.getAvailableShares() - orderxd.getBuyOrder().getAmount());

                            });
            });

            orderxd.getBuyOrder().setStatusType(OrderStatusType.SUCCESS);
            String jsonMessage = mapper.writeValueAsString(orderxd);

            if(orderxd.getBuyOrder().getClientId() == 1){
                jmsTemplate.convertAndSend(c1QueueFrankfurt, jsonMessage);
            }

        } else {

        }*/

    }

    @JmsListener(destination = "${jms.stocks.newOrder.Stuttgart}")
    public void receiveOrderStuttgart(String orderString) throws JsonProcessingException {

    }

}
