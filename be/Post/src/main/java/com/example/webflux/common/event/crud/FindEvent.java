package com.example.webflux.common.event.crud;

import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.model.entity.Domain;

public abstract class FindEvent extends CustomEvent {

    public FindEvent(Object source, Domain domain) {
        super(source, domain);
    }
}
