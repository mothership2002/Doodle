package com.example.webflux.handler;

import com.example.webflux.module.HeaderUtils;
import com.example.webflux.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.webflux.module.HeaderUtils.getHeader;

@Component
@RequiredArgsConstructor
public class PostHandler {

    private final PostService postService;

    public Mono<ServerResponse> getPosts(ServerRequest serverRequest) {
        return ServerResponse.ok().headers(getHeader()).body("hello", String.class);
    }

    public Mono<ServerResponse> createPost(ServerRequest serverRequest) {
        return ServerResponse.ok().headers(getHeader()).body("world", String.class);
    }


}
