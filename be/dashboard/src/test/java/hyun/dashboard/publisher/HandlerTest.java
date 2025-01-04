package hyun.dashboard.publisher;

import hyun.dashboard.publisher.application.router.RouteConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootTest
@Slf4j
public class HandlerTest {

    private WebTestClient client;
    @Autowired
    private RouteConfig routeConfig;
    private final String prefix = "/api";

    @BeforeEach
    void init() {
        RouterFunction<? extends ServerResponse> main = routeConfig.mainRouter();
        client = WebTestClient.bindToRouterFunction(main).build();
    }

    @Test
    public void user_handler() {
        EntityExchangeResult<String> result = client.get().uri(prefix + "/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("world").returnResult();
        log.info(result.getResponseBody());
    }
}
