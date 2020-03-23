package com.jphaugla.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jphaugla.domain.ProductEntity;
import com.rnbwarden.redisearch.CompressingJacksonSerializer;
import com.rnbwarden.redisearch.client.jedis.JedisRediSearchClient;
import io.redisearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import com.rnbwarden.redisearch.client.RediSearchClient;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
@ComponentScan("com.jphaugla")

public class RedisConfig {
    @Value("${redis_host:localhost}")
    private String redis_host;
    @Value("${redis_port:6379}")
    private int redis_port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redis_host, redis_port);
        return new JedisConnectionFactory(config);
    }
    @Bean
    public JedisRediSearchClient createJedisRediSearchClient() {

        Class<ProductEntity> clazz = ProductEntity.class;
        RedisSerializer<ProductEntity> redisSerializer = new CompressingJacksonSerializer<>(clazz, new ObjectMapper());
        Client rediSearchClient = new Client("stub", redis_host, redis_port);
        return new JedisRediSearchClient<>(clazz, rediSearchClient, redisSerializer, 1000L);
    }


}
