package com.example.webflux.common.event;

import com.example.webflux.common.model.entity.Domain;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EntityEvent extends ApplicationEvent {

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private final Domain domain;
    private final Type type;

    public EntityEvent(Object source, Domain domain, Type type) {
        super(source);
        this.domain = domain;
        this.type = type;
    }

}