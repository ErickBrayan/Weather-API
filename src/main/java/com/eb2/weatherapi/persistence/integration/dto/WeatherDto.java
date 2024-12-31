package com.eb2.weatherapi.persistence.integration.dto;



import java.io.Serializable;


public record WeatherDto(
        String resolvedAddress,
        String description,
        Double temp,
        Double feelslike
) implements Serializable {
}
