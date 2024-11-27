package com.example.webflux.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class CustomEvent extends ApplicationEvent {

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private final Type type;

    public CustomEvent(Object source, Type type) {
        super(source);
        this.type = type;
    }

}
