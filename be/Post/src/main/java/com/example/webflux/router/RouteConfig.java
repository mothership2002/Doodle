package com.example.webflux.router;

import com.example.webflux.handler.PostHandler;
import com.example.webflux.handler.MemberHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final MemberHandler userHandler;
    private final PostHandler postHandler;

    @Bean
    public RouterFunction<? extends ServerResponse> mainRouter() {
        return RouterFunctions.route()
                .add(userRouter())
                .add(postRouter())
                .build();
    }

    public RouterFunction<ServerResponse> userRouter() {
        return RouterFunctions
                .nest(path(url("/users")),
                        RouterFunctions.route()
                                .GET("", userHandler::getMembers)
                                .POST("", userHandler::createMember)
                                .build()
                );
    }

    public RouterFunction<ServerResponse> postRouter() {
        return RouterFunctions
                .nest(path(url("/posts")),
                        RouterFunctions.route()
                                .GET("", postHandler::getPosts)
                                .POST("", postHandler::createPost)
                                .build()
                );
    }

    private String url(String domain) {
        return "/api" + domain;
    }

}
