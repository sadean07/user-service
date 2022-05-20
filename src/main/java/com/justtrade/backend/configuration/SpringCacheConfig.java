package com.justtrade.backend.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class SpringCacheConfig {

  @Bean
  public CacheManager cacheManager() {
    var cacheManager = new CaffeineCacheManager("coin-price");
    cacheManager.setCaffeine(
      Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.DAYS)
        .recordStats()
    );
    return cacheManager;
  }
}
