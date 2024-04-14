package de.hka_iwi_1.avg_s2_producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

/**
 * Configuration class for the application.
 */
final class ApplicationConfig {

    ApplicationConfig() {
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
