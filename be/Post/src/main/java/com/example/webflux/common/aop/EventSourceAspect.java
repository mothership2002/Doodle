package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.SimpleEventPublisher;
import com.example.webflux.common.model.entity.Domain;
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
import java.util.Map;

@Component
@Aspect
@Slf4j(topic = "EventSourceLog")
@Order(1)
public class EventSourceAspect {

    private final SimpleEventPublisher publisher;
    private final Map<Class<? extends Domain>, Map<EntityEvent.Type, ? extends CustomEvent>> eventMap;

    public EventSourceAspect(SimpleEventPublisher publisher) {
        this.publisher = publisher;
        eventMap = Map.of();
    }

    @Around("(@within(org.springframework.stereotype.Repository) " +
                    "|| @within(org.springframework.data.repository.NoRepositoryBean))"
            + "&& !execution(* *find*(..))")
    public Object publishEvent(ProceedingJoinPoint jp) throws Throwable {
        Object proceed = jp.proceed();
        Signature signature = jp.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        if (proceed instanceof Mono) {
            return ((Mono<?>) proceed)
                    .doOnSuccess(value -> {
                        getEvent(value.getClass(), null);
                    });
        }
        return proceed;
    }

    private CustomEvent getEvent(Class<?> domainClazz, EntityEvent.Type type) {
        log.info("{}, {}", type, domainClazz.getSimpleName());
        return null;
    }
}
