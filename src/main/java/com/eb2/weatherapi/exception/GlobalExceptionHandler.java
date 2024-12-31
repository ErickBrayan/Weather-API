package com.eb2.weatherapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiErrorDTO> handlerGeneralException(HttpClientErrorException ex, HttpServletRequest request){

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(
                "Error",
                ex.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString()

        );

        return ResponseEntity.status(ex.getStatusCode()).body(apiErrorDTO);

    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<String> handleRateLimitExceeded(RateLimitExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

}
