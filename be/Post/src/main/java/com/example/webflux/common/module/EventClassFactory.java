package com.example.webflux.common.module;

import com.example.webflux.common.event.CustomEvent;
import com.example.webflux.common.event.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class EventClassFactory extends Module {

    private static final String EVENT = "Event";
    private static final String PACKAGE_NAME = CustomEvent.class.getPackageName();

    private final PackageScanner packageScanner;
    private final ByteBuddy byteBuddy;
    private final Map<Class<?>, Map<EntityEvent.Type, ? extends CustomEvent>> eventMap;

    public EventClassFactory(PackageScanner packageScanner, ByteBuddy byteBuddy) throws ClassNotFoundException {
        this.packageScanner = packageScanner;
        this.byteBuddy = byteBuddy;
        this.eventMap = new HashMap<>();
        super.init();
    }

    @Deprecated
    private Class<?> createEventClass(EntityEvent.Type type) {
        return null;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String getClassName(Class<?> domainClass, EntityEvent.Type type) {
        return PACKAGE_NAME + "." + domainClass.getSimpleName() + capitalize(type.name().toLowerCase()) + EVENT;
    }

    @Override
    protected void construct() {
        List<Class<?>> domainClasses = this.packageScanner.getDomainClasses();
        for (Class<?> domainClass : domainClasses) {
            HashMap<EntityEvent.Type, CustomEvent> value = new HashMap<>();
            for (EntityEvent.Type type : EntityEvent.Type.values()) {
                System.out.println(getClassName(domainClass, type));
                // bytebuddy를 쓰는게 맞나? ㅋㅋ
                byteBuddy
                        .subclass(CustomEvent.class)
                        .name(getClassName(domainClass, type));
//                        .defineField();
            }

            eventMap.put(domainClass, value);
        }
    }
}
