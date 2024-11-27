package com.example.webflux.common.event;

import lombok.Getter;

@Getter
public class EventWrapper extends CustomEvent {

//    private final Domain domain;

    public EventWrapper(Object source, Type type) {
        super(source, type);
//        this.domain = domain;
    }


}
