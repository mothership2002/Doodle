package com.example.webflux.common.event;

import org.springframework.context.ApplicationEvent;

public abstract class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

}
