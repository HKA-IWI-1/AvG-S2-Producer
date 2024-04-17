package de.hka_iwi_1.avg_s2_producer.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum OrderStatusType {

    SUCCESS("S"),
    ERROR("E"),
    PENDING("P");

    private final String value;

    OrderStatusType(final String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }

    /**
     * Konvertierung eines Strings in einen Enum-Wert.
     */
    @JsonCreator
    public static OrderStatusType of(final String value) {
        return Stream.of(values())
                .filter(orderStatusType -> orderStatusType.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
