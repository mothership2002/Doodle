package hyun.sample.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilterFactory implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.nanoTime();
        log.info("[request] {}", exchange.getRequest().getURI());
        
        exchange.getRequest().getHeaders();
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("[response] {} ns", System.nanoTime() - startTime);
        }));
    }
}
