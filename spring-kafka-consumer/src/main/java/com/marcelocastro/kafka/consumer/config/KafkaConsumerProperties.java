package com.marcelocastro.kafka.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kafka-consumer")
@Data
public class KafkaConsumerProperties {

  private String serverUrl;
  private String topicName;
}
