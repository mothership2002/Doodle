package com.example.webflux.common.event;

import com.example.webflux.common.model.entity.Domain;
import com.example.webflux.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(EntityEvent event) {
        Class<? extends Domain> domain = event.getDomain();
        System.out.println(domain);
        // TODO Temporary
        if (Member.class.equals(domain)) {
            System.out.println("Helo");
        }

        // 이벤트 발행 컨트롤 부분
        publisher.publishEvent(event);
    }


}
