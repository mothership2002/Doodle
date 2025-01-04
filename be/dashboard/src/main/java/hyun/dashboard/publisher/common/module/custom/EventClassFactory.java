package hyun.dashboard.publisher.common.module.custom;

import hyun.dashboard.publisher.DashboardApplication;
import hyun.dashboard.publisher.common.event.CustomEvent;
import hyun.dashboard.publisher.common.event.EntityEvent.Type;
import hyun.dashboard.publisher.common.event.crud.CreateEvent;
import hyun.dashboard.publisher.common.event.crud.DeleteEvent;
import hyun.dashboard.publisher.common.event.crud.FindEvent;
import hyun.dashboard.publisher.common.event.crud.UpdateEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hyun.dashboard.publisher.common.event.EntityEvent.Type.*;

@Component
@Slf4j
public class EventClassFactory extends Module {

    private static final String EVENT_PACKAGE_NAME = CustomEvent.class.getPackageName();

    private final Map<Type, Class<? extends CustomEvent>> eventSuperClassMap = Map.of(
            FIND, FindEvent.class,
            CREATE, CreateEvent.class,
            UPDATE, UpdateEvent.class,
            DELETE, DeleteEvent.class
    );
    private final PackageScanner packageScanner;
    private final ByteBuddy byteBuddy;
    @Getter private final Map<Class<?>, Map<Type, Class<? extends CustomEvent>>> eventMap;

    public EventClassFactory(PackageScanner packageScanner, ByteBuddy byteBuddy) throws ClassNotFoundException {
        this.packageScanner = packageScanner;
        this.byteBuddy = byteBuddy;
        this.eventMap = new HashMap<>();
        super.init();
    }

    @Override
    protected void construct() {
        List<Class<?>> domainClasses = this.packageScanner.getDomainClasses();
        for (Class<?> domainClass : domainClasses) {
            Map<Type, Class<? extends CustomEvent>> value = new HashMap<>();
            for (Type type : values()) {
                Class<? extends CustomEvent> eventClass = createEventClass(domainClass, eventSuperClassMap.get(type));
                value.put(type, eventClass);
            }
            eventMap.put(domainClass, value);
        }
    }

    private Class<? extends CustomEvent> createEventClass(Class<?> domainClass, Class<? extends CustomEvent> superClass) {
        return byteBuddy
                .subclass(superClass)
                .name(getClassName(domainClass) + superClass.getSimpleName())
                .make()
                .load(DashboardApplication.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
    }

    private String getClassName(Class<?> domainClass) {
        return EVENT_PACKAGE_NAME + "." + domainClass.getSimpleName();
    }
}
