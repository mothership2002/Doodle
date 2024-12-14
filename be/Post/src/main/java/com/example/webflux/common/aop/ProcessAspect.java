package com.example.webflux.common.aop;

import com.example.webflux.common.annotation.EventPublishPoint;
import com.example.webflux.common.event.EntityEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j(topic = "ProcessedLog")
@RequiredArgsConstructor
@Order(2)
public class ProcessAspect {

    private final String START_TIME_KEY = "start";

    @Around("execution(public * com.example.webflux..domain.*Service*.*(..))")
    public Object processLog(ProceedingJoinPoint joinPoint) throws Throwable {
        return Mono.deferContextual(contextView -> {
            try {
                Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
                return ((Mono<?>) joinPoint.proceed()).doOnSuccess(e -> processLog(contextView, method.getName(), joinPoint));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).contextWrite(context -> context.put(START_TIME_KEY, System.nanoTime()));
    }

    private void processLog(ContextView contextView, String methodName, JoinPoint joinPoint) {
        long start = contextView.get(START_TIME_KEY);
        log.info("[{}] Service Called, Method Name : [{}], processed time : [{} ms]",
                joinPoint.getTarget().getClass().getSimpleName(),
                methodName,
                (System.nanoTime() - start) / 1000000);
    }

}



