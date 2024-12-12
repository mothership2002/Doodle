package com.example.webflux.common.event;

import com.example.webflux.common.event.crud.CreateEvent;
import com.example.webflux.common.event.crud.DeleteEvent;
import com.example.webflux.common.event.crud.FindEvent;
import com.example.webflux.common.event.crud.UpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(CustomEvent event) {

    }

    public void publish(FindEvent event) {

    }

    public void publish(CreateEvent event) {

    }

    public void publish(UpdateEvent event) {

    }

    public void publish(DeleteEvent event) {

    }
}

