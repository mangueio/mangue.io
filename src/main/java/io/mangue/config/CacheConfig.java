package io.mangue.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) { // redistemplate bean is defined else where and invoked here
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    // Number of seconds before expiration. Defaults to unlimited (0)
    cacheManager.setDefaultExpiration(300); // 5 min
    return cacheManager;
  }

//  @Override
//  @Bean
//  public KeyGenerator keyGenerator() {
//    return new SimpleKeyGenerator();
//  }

}