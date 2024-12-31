package com.eb2.weatherapi.service;

import com.eb2.weatherapi.persistence.integration.dto.WeatherDto;

public interface WeatherService {

    WeatherDto getWeatherByCountry(String country);
}
