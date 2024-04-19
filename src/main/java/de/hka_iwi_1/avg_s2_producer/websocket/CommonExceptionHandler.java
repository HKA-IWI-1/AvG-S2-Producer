package de.hka_iwi_1.avg_s2_producer.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Handler for generic exceptions.
 */
@ControllerAdvice
@Slf4j
class CommonExceptionHandler {

    /**
     * Handle not found exception.
     *
     * @param ex The exception object.
     */
    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    void onNotFound(final NotFoundException ex) {
        log.debug("onNotFound: {}", ex.getMessage());
    }
}
