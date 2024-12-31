package com.eb2.weatherapi.service.impl;

import com.eb2.weatherapi.exception.ApiErrorException;
import com.eb2.weatherapi.service.HttpClientService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class RestTemplateImpl implements HttpClientService {

    private final RestTemplate restTemplate;

    public RestTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String url, Map<String, String> param, Class<T> responseType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        for (Map.Entry<String, String> entry : param.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        String finalUrl = builder.build().toUriString();
        HttpEntity httpEntity = new HttpEntity(getHeaders());

        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("HTTP Status Code: %d", response.getStatusCode().value());
            throw  new ApiErrorException(message);
        }

        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public <T, R> T doPeost(String url, Map<String, String> param, Class<T> responseType, R bodyRequest) {
        return null;
    }

    @Override
    public <T, R> T doPut(String url, Map<String, String> param, Class<T> responseType, R bodyRequest) {
        return null;
    }

    @Override
    public <T> T doDelete(String url, Map<String, String> param, Class<T> responseType) {
        return null;
    }
}
