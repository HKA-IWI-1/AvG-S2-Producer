package de.hka_iwi_1.avg_s2_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import static de.hka_iwi_1.avg_s2_producer.Banner.*;

/**
 * Class with the main-method for the application, building upon Spring Boot.
 */
@SpringBootApplication
@EnableScheduling
@Import({ApplicationConfig.class})
public class Application {

    /**
     * Main program for starting the microservice.
     *
     * @param args Potential additional arguments for the start of the microservice.
     */
    public static void main(String... args) {
        final var app = new SpringApplication(Application.class);
        app.setBanner((environment, sourceClass, out) -> out.println(TEXT));
        app.run(args);
    }

}
