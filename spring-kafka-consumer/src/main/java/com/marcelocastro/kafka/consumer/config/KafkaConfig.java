package com.marcelocastro.kafka.consumer.config;

import com.marcelocastro.kafka.domain.Event;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

  private final KafkaConsumerProperties kafkaConsumerProperties;

  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name(kafkaConsumerProperties.getTopicName())
        .partitions(3)
        .replicas(1)
        .build();
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<UUID, Event> kafkaListenerContainerFactory(
      ConsumerFactory<UUID, Event> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<UUID, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }

  @Bean
  public ConsumerFactory<UUID, Event> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getServerUrl());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "special-group");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(JsonDeserializer.TRUSTED_PACKAGES , "*");
    return new DefaultKafkaConsumerFactory<>(props);
  }
}
