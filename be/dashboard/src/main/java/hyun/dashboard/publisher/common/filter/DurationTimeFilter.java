package hyun.dashboard.publisher.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j(topic = "DurationTimeLog")
public class DurationTimeFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long start = System.nanoTime();
        RequestPath path = exchange.getRequest().getPath();
        return chain.filter(exchange)
                //signalType은 의미가 있나?
                .doFinally(signalType -> log.info("[DurationTimeFilter finished in {} ms] / [Server Endpoint] [{}] / [status : {}]",
                        (System.nanoTime() - start) / 1_000_000,
                        path,
                        signalType));
    }
}
