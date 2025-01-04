package hyun.dashboard.common.aop;

import hyun.dashboard.common.annotation.EventPublishPoint;
import hyun.dashboard.common.event.CustomEvent;
import hyun.dashboard.common.event.EntityEvent;
import hyun.dashboard.common.event.SimpleEventPublisher;
import hyun.dashboard.common.model.entity.Domain;
import hyun.dashboard.common.module.custom.EventClassFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

    @Around("@annotation(hyun.dashboard.common.annotation.EventPublishPoint)")
    public Object publishEvent(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        EventPublishPoint annotation = signature.getMethod().getAnnotation(EventPublishPoint.class);
        Object proceed = jp.proceed();
        AtomicReference<Domain> ref = new AtomicReference<>();
        if (proceed instanceof Mono) {
            return ((Mono<?>) proceed)
                    .doOnSuccess(value -> ref.set((Domain) value))
                    .doFinally(signalType ->
                            Mono.fromRunnable(() -> publisher.publish(getEvent(ref.get(), annotation.type(), jp.getTarget())))
                                    .subscribeOn(Schedulers.boundedElastic()) // TODO check this method
                                    .subscribe()
                    );
        }
        return proceed;
    }

    private CustomEvent getEvent(Domain domain, EntityEvent.Type type, Object source) {
        Class<? extends CustomEvent> eventTypeClass = EventClassFactory.getEventMap().get(domain.getClass()).get(type);
        try {
            Constructor<? extends CustomEvent> constructor = eventTypeClass.getConstructor(Object.class, Domain.class);
            CustomEvent customEvent = constructor.newInstance(source, domain);
            log.info("Event Published, Domain : [{}], Type : [{}]", domain.getClass().getSimpleName(), type);
            return customEvent;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
