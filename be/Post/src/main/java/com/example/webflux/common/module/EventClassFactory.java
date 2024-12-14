package com.example.webflux.common.module;

import com.example.webflux.WebfluxApplication;
import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import com.example.webflux.common.event.EntityEvent.Type;
import com.example.webflux.common.event.crud.CreateEvent;
import com.example.webflux.common.event.crud.DeleteEvent;
import com.example.webflux.common.event.crud.FindEvent;
import com.example.webflux.common.event.crud.UpdateEvent;
import com.example.webflux.common.model.entity.Domain;
import com.example.webflux.post.domain.Post;
import com.example.webflux.reply.domain.Reply;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.webflux.common.event.EntityEvent.Type.*;

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
                .load(WebfluxApplication.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
    }

    private String getClassName(Class<?> domainClass) {
        return EVENT_PACKAGE_NAME + "." + domainClass.getSimpleName();
    }
}
