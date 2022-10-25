package com.marcelocastro.kafka.producer.service;

import com.marcelocastro.kafka.domain.Event;
import com.marcelocastro.kafka.producer.config.KafkaProducerProperties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProducerService {

  private final KafkaProducerProperties kafkaProducerProperties;
  private KafkaTemplate<UUID, Event> kafkaTemplate;

  public void publishEvent(Event event) {
    log.info("Publishing event {}", event);
    kafkaTemplate.send(kafkaProducerProperties.getTopicName(), event);
  }
}
