package de.hka_iwi_1.avg_s2_producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReceiveService {

    @JmsListener(destination = "${jms.SendPricesTest}")
    public void receiveMessage(String message) {
        log.info("receiveMessage: {}", message);
    }

}
