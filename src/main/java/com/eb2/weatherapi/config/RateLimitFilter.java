package com.eb2.weatherapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.io.IOException;

@Component
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {

    private final WeatherCacheConfig weatherCacheConfig;

    public RateLimitFilter(WeatherCacheConfig weatherCacheConfig) {
        this.weatherCacheConfig = weatherCacheConfig;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String key = request.getRemoteAddr();

        if (!weatherCacheConfig.tryConsume(key)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
