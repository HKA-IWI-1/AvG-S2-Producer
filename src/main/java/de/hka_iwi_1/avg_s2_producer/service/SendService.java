package de.hka_iwi_1.avg_s2_producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendService {

    private final JmsTemplate jmsTemplate;

    @Value("${jms.SendPricesTest}")
    String jmsQueue;

    public void SendPrices(String message) {

        // TODO: Message ersetzen durch Aktien aus Repository

        jmsTemplate.convertAndSend(jmsQueue, message);
    }

}
