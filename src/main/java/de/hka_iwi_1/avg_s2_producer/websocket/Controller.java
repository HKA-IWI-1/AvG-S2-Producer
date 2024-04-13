package de.hka_iwi_1.avg_s2_producer.websocket;

import de.hka_iwi_1.avg_s2_producer.service.SendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for accepting GET requests to parse the iCal file.
 */
@RestController
@RequestMapping("/rest/sendPrices")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final SendService sendService;

     @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public String sendPriceMessage(@RequestBody String message) {
        try {
            sendService.SendPrices(message);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }




}
