package com.eb2.weatherapi.service.impl;


import com.eb2.weatherapi.config.WeatherCacheConfig;
import com.eb2.weatherapi.persistence.integration.dto.WeatherDto;
import com.eb2.weatherapi.persistence.integration.repository.WeatherRepository;
import com.eb2.weatherapi.service.WeatherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

        private final WeatherRepository weatherRepository;


    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    @Cacheable(value = WeatherCacheConfig.CACHE_NAME)
    public WeatherDto getWeatherByCountry(String city) {

        return weatherRepository.getWeatherByCity(city);
    }
}
