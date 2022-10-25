package com.marcelocastro.kafka.producer.controller;

import com.marcelocastro.kafka.domain.Event;
import com.marcelocastro.kafka.producer.service.ProducerService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@Slf4j
public class ProducerController {

  private final ProducerService producerService;

  @PostMapping
  public void postEvent(@RequestBody Event event) {
    event.setId(UUID.randomUUID());
    event.setCreated(LocalDateTime.now());
    producerService.publishEvent(event);
  }
}
