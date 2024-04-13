package de.hka_iwi_1.avg_s2_producer.websocket;

import lombok.Getter;

/**
 * RuntimeException.
 */
@Getter
public final class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
