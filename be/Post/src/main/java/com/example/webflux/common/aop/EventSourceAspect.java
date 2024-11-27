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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j(topic = "EventSource")
@RequiredArgsConstructor
@Order(Ordered.LOWEST_PRECEDENCE)
public class EventSourceAspect {

    private final SimpleEventPublisher publisher;

    // 중요
    // 어노테이션을 매개 변수로 잡을 시 바인딩 아규먼츠 익셉션이 발생하여 리플렉션으로 핸들링.
    @Around("@annotation(com.example.webflux.common.annotation.EventPublishPoint)")
    public Object publishEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        final String key = "start";
        return Mono.deferContextual(context -> {
            try {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                EventPublishPoint annotation = method.getAnnotation(EventPublishPoint.class);
                return ((Mono<?>) joinPoint.proceed()).doOnSuccess(e -> {
                    long start = context.get(key);
                    EventWrapper event = new EventWrapper(joinPoint.getTarget(), annotation.value());
                    log.info("[{}] Event Published, Type : [{}], processed time : [{}ms]",
                            event.getSource().getClass().getSimpleName(),
                            event.getType(),
                            (System.nanoTime() - start) / 1000000);
                    publisher.publish(event);
                });
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).contextWrite(context -> context.put(key, System.nanoTime()));
    }
}
