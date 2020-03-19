package com.jphaugla.config;

import com.jphaugla.domain.ProductEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.rnbwarden.redisearch.client.RediSearchClient;


@DependsOn("RediSearchAutoConfiguration")
@Configuration
@ComponentScan("com.jphaugla")

public class RedisConfig {
    RediSearchClient<ProductEntity> rediSearchClient;
}
