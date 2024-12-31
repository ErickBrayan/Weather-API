package com.eb2.weatherapi.exception;

public record ApiErrorDTO(
        String message,
        String backendMessage,
        String method,
        String url
) {
}
