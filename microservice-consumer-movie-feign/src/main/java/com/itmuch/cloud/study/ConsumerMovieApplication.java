package com.itmuch.cloud.study;

import com.netflix.config.ConfigurationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerMovieApplication {
  public static void main(String[] args) {
    try {
      ConfigurationManager.loadPropertiesFromResources("ribbon.properties");
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpringApplication.run(ConsumerMovieApplication.class, args);
  }
}
