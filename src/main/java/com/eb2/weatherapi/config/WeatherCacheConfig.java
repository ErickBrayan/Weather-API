package com.eb2.weatherapi.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
@EnableCaching
public class WeatherCacheConfig {

    public static final String CACHE_NAME = "weatherCacheManager";
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(Duration.ofHours(1).toMillis());

        var config = Collections.singletonMap(CACHE_NAME, cacheConfig);
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    @Bean
    public Bucket weatherCacheBucket() {
        Bandwidth limit = Bandwidth.builder()
                .capacity(100)
                .refillIntervally(100, Duration.ofMinutes(30))
                .build();

        return Bucket.builder().addLimit(limit).build();

    }


    public boolean tryConsume(String key) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> weatherCacheBucket());
        return bucket.tryConsume(1);
    }


}
