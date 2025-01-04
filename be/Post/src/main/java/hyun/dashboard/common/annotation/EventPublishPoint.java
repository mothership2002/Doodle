package hyun.dashboard.common.annotation;

import hyun.dashboard.common.event.EntityEvent;
import hyun.dashboard.common.model.entity.Domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventPublishPoint {

    EntityEvent.Type type();

    Class<? extends Domain> domain();
}
