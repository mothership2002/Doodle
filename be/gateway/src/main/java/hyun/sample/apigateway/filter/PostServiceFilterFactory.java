package hyun.sample.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PostServiceFilterFactory extends AbstractGatewayFilterFactory<PostServiceFilterFactory.Config> {

    public PostServiceFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(PostServiceFilterFactory.Config config) {
        return (exchange, chain) -> {

            HttpHeaders headers = exchange.getRequest().getHeaders();
            headers.keySet()
                    .forEach(key -> log.info("[key] {}, [value] {}", key, headers.get(key)));

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("hello");
            }));
        };
    }

    public static class Config {

    }
}
