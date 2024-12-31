package com.eb2.weatherapi.persistence.integration;

import com.eb2.weatherapi.persistence.integration.dto.WeatherDto;
import com.eb2.weatherapi.util.Converter;
import com.fasterxml.jackson.databind.JsonNode;

public class WeatherMapper {

    public static WeatherDto toDto(JsonNode node) {

        if (node == null) {
            throw new IllegalArgumentException("node is null");
        }

        String resolvedAddress = node.get("resolvedAddress").asText();
        String description = node.get("description").asText();

        JsonNode currentLocation = node.get("currentConditions");

        double temp = Converter.toCelsius(currentLocation.get("temp").asDouble());
        double feelslike = Converter.toCelsius(currentLocation.get("feelslike").asDouble());

        return new WeatherDto(resolvedAddress, description, temp, feelslike);
    }
}
