package com.eb2.weatherapi.exception;

public class ApiErrorException extends RuntimeException {

    public ApiErrorException(String message) {
        super(message);
    }
}
