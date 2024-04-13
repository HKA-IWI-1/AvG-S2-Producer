package de.hka_iwi_1.avg_s2_producer.websocket;

import de.hka_iwi_1.avg_s2_producer.entity.StockMarket;
import de.hka_iwi_1.avg_s2_producer.repository.StockMarketRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Controller for accepting GET requests to parse the iCal file.
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    // z.B. http://localhost:8080/test/someString
    @GetMapping(
            path = "{content}"
            , produces = "application/json"
    )
    public ResponseEntity<String> getTest(
            @PathVariable final String content
    ) {
        log.debug("getTest content={}, ", content);
        return ok("{ content:\"" + content + "\",}");
    }

}
