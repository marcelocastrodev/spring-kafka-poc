package com.marcelocastro.kafka.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kafka-producer")
@Data
public class KafkaProducerProperties {

  private String serverUrl;
  private String topicName;
}
