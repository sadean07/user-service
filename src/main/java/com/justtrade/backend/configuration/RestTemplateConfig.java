package com.justtrade.backend.configuration;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {
  @Value("${http.outgoing.readTimeout}")
  private int readTimeout;

  @Value("${http.outgoing.connectionTimeout}")
  private int connectionTimeout;

  @Bean
  @Qualifier("serviceRestTemplate")
  public RestTemplate serviceRestTemplate() {
    return new RestTemplateBuilder()
      .setReadTimeout(Duration.ofMillis(readTimeout))
      .setConnectTimeout(Duration.ofMillis(connectionTimeout))
      .build();
  }
}
