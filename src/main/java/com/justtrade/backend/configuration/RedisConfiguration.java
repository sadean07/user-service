package com.justtrade.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
@ConditionalOnProperty(prefix = "redis", name = "enabled", havingValue = "true")
public class RedisConfiguration {

  @Value("${spring.redis.host}")
  private String redisHostName;

  @Value("${spring.redis.port}")
  private int redisPort;

  @Bean
  public JedisConnectionFactory connectionFactory() {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(redisHostName);
    configuration.setPort(redisPort);
    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate() {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(connectionFactory());
    return template;
  }

  //TODO : di comment sementara
//  @Bean
//  public static ConfigureRedisAction configureRedisAction() {
//    return ConfigureRedisAction.NO_OP;
//  }
}
