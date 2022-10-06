package com.marcelocastro.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringKafkaProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaProducerApplication.class, args);
  }
}
