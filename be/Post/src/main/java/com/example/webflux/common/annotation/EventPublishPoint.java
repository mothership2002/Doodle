package com.example.webflux.common.annotation;

import com.example.webflux.common.event.EntityEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventPublishPoint {

    EntityEvent.Type value() default EntityEvent.Type.CREATE;

}
