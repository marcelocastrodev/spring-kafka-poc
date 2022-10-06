package com.marcelocastro.kafka.consumer.listener;

import com.marcelocastro.kafka.domain.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EventListener {

  @KafkaListener(topics = "kafka-poc")
  public void consume(Event event) {
    log.info("Event consumed {}", event);
  }

}
