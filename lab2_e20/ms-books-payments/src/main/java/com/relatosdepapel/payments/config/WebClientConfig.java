package com.relatosdepapel.payments.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @LoadBalanced enables lb://<serviceName> resolution via service discovery.
 */
@Configuration
public class WebClientConfig {

 @Bean
 @LoadBalanced
 public WebClient.Builder loadBalancedWebClientBuilder() {
  return WebClient.builder();
 }
}
