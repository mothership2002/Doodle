package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.SimpleEventPublisher;
import com.example.webflux.common.model.entity.Domain;
import com.example.webflux.member.domain.Member;
import com.example.webflux.post.domain.Post;
import com.example.webflux.reply.domain.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Aspect
@Slf4j
@Order(Integer.MAX_VALUE - 1)
public class EventSourceAspect {

    private final SimpleEventPublisher publisher;
    private final Map<Class<? extends Domain>, Map<EntityEvent.Type, ? extends CustomEvent>> eventMap;

    public EventSourceAspect(SimpleEventPublisher publisher) {
        this.publisher = publisher;
        eventMap = Map.of();
    }

    @AfterReturning(pointcut = "execution(public * com.example.webflux.*.infrastructure.*Repository*.*(..))", returning = "result")
    public void publishEvent(JoinPoint jp, Mono<?> result) throws Throwable {
        result.flatMap(value -> {

            Class<?> domainClazz = value.getClass(); // domain<<
            log.info("{}, {}", Thread.currentThread().getName(), domainClazz.getSimpleName());
            return Mono.fromRunnable(() -> {

            })
        });
    }

    private CustomEvent getEvent(Class<?> domainClazz, EntityEvent.Type type) {
        return null;
    }
}
