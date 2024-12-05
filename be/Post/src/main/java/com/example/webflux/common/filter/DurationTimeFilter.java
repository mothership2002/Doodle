package com.example.webflux.common.filter;

import lombok.extern.slf4j.Slf4j;
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
        return chain.filter(exchange)
                //signalType은 의미가 있나?
                .doFinally(signalType -> log.info("[DurationTimeFilter finished in {} ms] / [status : {}]", (System.nanoTime() - start) / 1_000_000, signalType));
    }
}
