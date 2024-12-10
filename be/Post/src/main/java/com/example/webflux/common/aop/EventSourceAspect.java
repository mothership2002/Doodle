package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.SimpleEventPublisher;
import com.example.webflux.common.model.entity.Domain;
import com.example.webflux.common.module.EventClassFactory;
import com.example.webflux.common.module.PackageScanner;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Aspect
@Slf4j(topic = "EventSourceLog")
@Order(1)
public class EventSourceAspect {

    private final SimpleEventPublisher publisher;
    private final EventClassFactory EventClassFactory;

    public EventSourceAspect(SimpleEventPublisher publisher, EventClassFactory eventClassFactory) {
        this.publisher = publisher;
        this.EventClassFactory = eventClassFactory;
    }

    @Around("(@within(org.springframework.stereotype.Repository) "
        + "|| @within(org.springframework.data.repository.NoRepositoryBean))"
        + "&& !execution(* *find*(..))")
    public Object publishEvent(ProceedingJoinPoint jp) throws Throwable {
        Object proceed = jp.proceed();
        AtomicReference<Domain> ref = new AtomicReference<>();
        if (proceed instanceof Mono) {
            return ((Mono<?>) proceed)
                    .doOnSuccess(value -> ref.set((Domain) value))
                    .doFinally(signalType -> publisher.publish(getEvent(ref.get(), null)));
        }
        return proceed;
    }

    private CustomEvent getEvent(Domain domain, EntityEvent.Type type) {
        log.info("{}, {}", type, domain);
        return null;
    }
}
