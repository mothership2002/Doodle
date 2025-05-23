package hyun.dashboard.publisher.common.event;

import hyun.dashboard.publisher.member.domain.Member;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.test.StepVerifier;

@Configuration
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
