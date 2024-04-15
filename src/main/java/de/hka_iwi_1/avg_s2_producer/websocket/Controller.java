package de.hka_iwi_1.avg_s2_producer.websocket;

import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;
import de.hka_iwi_1.avg_s2_producer.repository.StockMarketRepository;
import de.hka_iwi_1.avg_s2_producer.service.SendService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



/**
 * Controller for accepting GET requests to parse the iCal file.
 */
@RestController
@RequestMapping("/rest/sendPrices")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final SendService sendService;
    private final StockMarketRepository repo;

//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    public String sendPriceMessage(@RequestBody String message) {
//        try {
//            sendService.SendPrices(message);
//            return "Message sent successfully!";
//        } catch (Exception e) {
//            return "Error sending message: " + e.getMessage();
//        }
//    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    List<StockMarket> get(@RequestParam @NonNull final Map<String, List<String>> suchkriterien)
    {
        log.debug("get: suchkriterien={}", suchkriterien);
        final var passagiere = repo.find(suchkriterien).stream().toList();
        log.debug("get: {}", passagiere);
        return passagiere;
    }

    @PostMapping
    public String sendPriceMessage(@RequestParam @NonNull final MultiValueMap<String, String> suchkriterien) {
        try {
            sendService.sendPrices(suchkriterien);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }

}
