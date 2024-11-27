package com.example.webflux.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * 퍼블리셔에서 받은 이벤트를 라우팅하는 이벤트로 사용할 것
 */
public abstract class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }
}
