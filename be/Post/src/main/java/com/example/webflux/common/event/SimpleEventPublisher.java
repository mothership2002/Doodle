package com.example.webflux.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CustomEvent event) {

    }


}
