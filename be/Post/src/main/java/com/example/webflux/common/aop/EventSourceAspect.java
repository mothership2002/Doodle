package com.example.webflux.common.aop;

import com.example.webflux.common.Constant;
import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.SimpleEventPublisher;
import com.example.webflux.common.model.entity.Domain;
import com.example.webflux.common.module.EventClassFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith(Constant.ROOT_PACKAGE)) {
                if (isIncludedPackage(element.getClassName())
                    && isCRUD(element)) {
                    Class<?> callClass = Class.forName(element.getClassName());
                    System.out.println(element.getMethodName());
                }
            }
        }

        if (proceed instanceof Mono) {
            return ((Mono<?>) proceed)
                    .doOnSuccess(value -> ref.set((Domain) value))
                    // TODO 여기서 이벤트를 어떻게 넣지 흠
                    .doFinally(signalType -> publisher.publish(getEvent(ref.get(), null)));
        }
        return proceed;
    }

    private boolean isCRUD(StackTraceElement element) {
        EntityEvent.Type[] values = EntityEvent.Type.values();
        String methodName = element.getMethodName().toLowerCase();
        for (EntityEvent.Type value : values) {
            if (methodName.contains(value.name().toLowerCase()))
                return true;
        }
        return false;
    }

    private CustomEvent getEvent(Domain domain, EntityEvent.Type type) {
        log.info("{}, {}", type, domain);
        System.out.println(EventClassFactory.getEventMap().get(domain.getClass()));
        Class<? extends CustomEvent> eventTypeClass = EventClassFactory.getEventMap().get(domain.getClass()).get(type);
        System.out.println(eventTypeClass);
        return null;
    }

    private boolean isIncludedPackage(String className) {
        return !className.startsWith(Constant.EXCLUDE_PACKAGE_NAME_COMMON)
                && !className.startsWith(Constant.EXCLUDE_PACKAGE_NAME_CONFIG);
    }
}
