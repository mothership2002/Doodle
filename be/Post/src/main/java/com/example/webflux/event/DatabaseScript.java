package com.example.webflux.event;

import com.example.webflux.model.entity.Member;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseScript {

    private final R2dbcEntityTemplate template;
    private final Gson gson;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        template.getDatabaseClient().sql(
                        "CREATE TABLE MEMBER (" +
                        "    ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "    ACCOUNT VARCHAR(13) NOT NULL," +
                        "    PASSWORD VARCHAR(255) NOT NULL," +
                        "    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "    UPDATED_AT TIMESTAMP DEFAULT null ON UPDATE CURRENT_TIMESTAMP" +
                        ")"
                )
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        template.insert(Member.class)
                .using(new Member("hello", "hello"))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
//
//        template.select(Member.class)
//                .first()
//                .doOnNext(it -> log.info("{}", gson.toJson(it)))
//                .as(StepVerifier::create)
//                .expectNextCount(1)
//                .verifyComplete();
    }

}
