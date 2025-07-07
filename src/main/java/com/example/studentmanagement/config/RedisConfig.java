package com.example.studentmanagement.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory cf) {
    RedisSerializationContext.SerializationPair<Object> jsonSerializer =
      RedisSerializationContext.SerializationPair.fromSerializer(
        new GenericJackson2JsonRedisSerializer()
      );

    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
      .serializeValuesWith(jsonSerializer)
      .entryTtl(Duration.ofMinutes(10)); // TTL set to 10 minutes

    return RedisCacheManager.builder(cf)
      .cacheDefaults(config)
      .build();
  }
}

