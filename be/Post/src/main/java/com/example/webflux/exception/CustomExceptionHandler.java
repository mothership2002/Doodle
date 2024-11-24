package com.example.webflux.exception;

import com.example.webflux.common.module.HeaderUtils;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Deprecated
//@Component
@Order(-2)
@RequiredArgsConstructor
public class CustomExceptionHandler implements WebExceptionHandler {

    private final Gson gson;

    @Override
    @NonNull
    public Mono<Void> handle(ServerWebExchange exchange, @NonNull Throwable e) {
        HttpStatus status = getStatus(e);
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(status);
        HeaderUtils.getHeader().accept(response.getHeaders());

        HashMap<String, Object> errorAttribute = new HashMap<>();
        errorAttribute.put("error", status.getReasonPhrase());
        errorAttribute.put("message", e.getMessage());

        return response
                .writeWith(Mono.just(response
                        .bufferFactory()
                        .wrap(gson.toJson(errorAttribute).getBytes())));
    }

    private HttpStatus getStatus(Throwable e) {
        if (e instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
