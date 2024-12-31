package com.eb2.weatherapi.service;

import java.util.Map;

public interface HttpClientService {

    <T> T doGet(String url, Map<String,String> param, Class<T> responseType);
    <T,R> T doPeost(String url, Map<String,String> param, Class<T> responseType, R bodyRequest);
    <T,R> T doPut(String url, Map<String,String> param, Class<T> responseType, R bodyRequest);
    <T> T doDelete(String url, Map<String,String> param, Class<T> responseType);
}
