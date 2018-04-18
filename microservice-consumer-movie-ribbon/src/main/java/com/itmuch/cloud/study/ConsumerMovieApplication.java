package com.itmuch.cloud.study;

import com.netflix.config.ConfigurationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerMovieApplication {
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {

    try {
      ConfigurationManager.loadPropertiesFromResources("ribbon.properties");
    } catch (IOException e) {
      e.printStackTrace();
    }

    SpringApplication.run(ConsumerMovieApplication.class, args);
  }
}
