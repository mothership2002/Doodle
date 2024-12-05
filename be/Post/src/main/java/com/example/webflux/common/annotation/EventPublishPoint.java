package com.example.webflux.common.annotation;

import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.model.entity.Domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventPublishPoint {

    EntityEvent.Type type();

    Class<? extends Domain> domain();
}
