package com.marcelocastro.kafka.producer.config;

import com.marcelocastro.kafka.domain.Event;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

  private final KafkaProducerProperties kafkaProducerProperties;

  @Bean
  public KafkaTemplate<UUID, Event> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<UUID, Event> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerProperties());
  }

  private Map<String, Object> producerProperties() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getServerUrl());
    properties.put(ProducerConfig.LINGER_MS_CONFIG, 10);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return properties;
  }

}
