package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.SimpleEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

@Component
@Aspect
@Slf4j(topic = "ProcessedLog")
@RequiredArgsConstructor
public class ProcessAspect {

    private final String START_TIME_KEY = "start";

    // 중요
    // 어노테이션을 매개 변수로 잡을 시 바인딩 아규먼츠 익셉션이 발생하여 리플렉션으로 핸들링.
    @Around("@annotation(com.example.webflux.common.annotation.EventPublishPoint)")
    public Object publishEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        return Mono.deferContextual(contextView -> {
            try {
                EventPublishPoint annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(EventPublishPoint.class);
                return ((Mono<?>) joinPoint.proceed()).doOnSuccess(e -> publishEvent(contextView, annotation, joinPoint));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).contextWrite(context -> context.put(START_TIME_KEY, System.nanoTime()));
    }

    private void publishEvent(ContextView contextView, EventPublishPoint annotation, JoinPoint joinPoint) {
        long start = contextView.get(START_TIME_KEY);
        EntityEvent event = new EntityEvent(joinPoint.getTarget(), annotation.domain(), annotation.type());
        log.info("[{}] Event Published, Type : [{}], processed time : [{} ms]",
                    event.getSource().getClass().getSimpleName(),
                    event.getType(),
                    (System.nanoTime() - start) / 1000000);
    }

}



