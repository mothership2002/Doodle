package com.example.webflux.common.event;

import com.example.webflux.common.model.entity.Domain;
import org.springframework.context.ApplicationEvent;

/**
 * 퍼블리셔에서 받은 이벤트를 라우팅하는 이벤트로 사용할 것
 */
public abstract class CustomEvent extends ApplicationEvent {

    protected final Domain domain;

    public CustomEvent(Object source, Domain domain) {
        super(source);
        this.domain = domain;
    }
}
