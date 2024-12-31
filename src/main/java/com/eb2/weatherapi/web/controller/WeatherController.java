package com.eb2.weatherapi.web.controller;

import com.eb2.weatherapi.persistence.integration.dto.WeatherDto;
import com.eb2.weatherapi.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;


    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{country}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("country") String country) {
        return ResponseEntity.ok(weatherService.getWeatherByCountry(country));
    }
}
