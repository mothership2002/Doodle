package com.example.webflux.common.module.kafka;

import com.example.webflux.common.event.crud.CreateEvent;
import com.example.webflux.common.model.entity.Domain;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class kafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @EventListener
    public void sendCreated(CreateEvent event) {
        Domain domain = event.getDomain();
        kafkaTemplate.send(domain.getClass().getSimpleName(), domain.toString());
    }

    @EventListener
    public void sendUpdated(String message) {
        kafkaTemplate.send("hello", message);
    }
}
