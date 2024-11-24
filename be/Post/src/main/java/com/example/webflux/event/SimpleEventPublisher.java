package com.example.webflux.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "EventPublisher")
@RequiredArgsConstructor
public class SimpleEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CustomEvent event) {
        log.info("{}", event.getSource());
        publisher.publishEvent(event);
    }

}
