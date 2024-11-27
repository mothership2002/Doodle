package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.EventWrapper;
import com.example.webflux.common.event.SimpleEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j(topic = "EventSource")
@RequiredArgsConstructor
public class EventSourceAspect {

    private final SimpleEventPublisher publisher;

    // 어노테이션을 매개 변수로 잡을 시 바인딩 아규먼츠 익셉션이 발생하여 리플렉션으로 핸들링.
    @Around("@annotation(com.example.webflux.common.annotation.EventPublishPoint)")
    public Object publishEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        EventPublishPoint annotation = method.getAnnotation(EventPublishPoint.class);
        log.debug("Event published with value: {}, {}", joinPoint, annotation.value());
        Object proceed = joinPoint.proceed();
        publisher.publish(new EventWrapper(joinPoint.getTarget(), annotation.value()));
        return proceed;
    }
}
