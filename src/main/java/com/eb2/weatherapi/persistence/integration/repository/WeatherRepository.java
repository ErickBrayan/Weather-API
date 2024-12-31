package com.eb2.weatherapi.persistence.integration.repository;

import com.eb2.weatherapi.persistence.integration.WeatherMapper;
import com.eb2.weatherapi.persistence.integration.dto.WeatherDto;
import com.eb2.weatherapi.service.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WeatherRepository {

    private final HttpClientService httpClientService;

    @Value("${api.key.weather}")
    private String apiKey;

    @Value("${api.base.path}")
    private String weatherPath ;

    public WeatherRepository(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public WeatherDto getWeatherByCity(String city) {

        Map<String,String> params = new HashMap<>();
        params.put("key", apiKey);

        String finalUrl = weatherPath.concat("/").concat(city);

        JsonNode response = httpClientService.doGet(finalUrl, params, JsonNode.class);

        return WeatherMapper.toDto(response);
    }
}
