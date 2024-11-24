package com.example.webflux.application.router;

import com.example.webflux.application.handler.PostHandler;
import com.example.webflux.application.handler.MemberHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Deprecated
//@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final MemberHandler memberHandler;
    private final PostHandler postHandler;
//    private final ReplyHandler replyHandler;

    private final String ID_PATH_VARIABLE = "/{id}";


    @Bean
    public RouterFunction<? extends ServerResponse> mainRouter() {
        return RouterFunctions.route()
                .add(userRouter())
                .add(postRouter())
                .build();
    }

    public RouterFunction<ServerResponse> userRouter() {
        return RouterFunctions
                .nest(path(url("/user")),
                        RouterFunctions.route()
                                .GET("", memberHandler::get)
                                .POST("", memberHandler::create)
                                .PUT(ID_PATH_VARIABLE, memberHandler::update)
                                .DELETE(ID_PATH_VARIABLE, memberHandler::delete)
                                .build()
                );
    }

    public RouterFunction<ServerResponse> postRouter() {
        return RouterFunctions
                .nest(path(url("/post")),
                        RouterFunctions.route()
                                .GET("", postHandler::get)
                                .GET(ID_PATH_VARIABLE, postHandler::getOne)
                                .POST("", postHandler::create)
                                .DELETE(ID_PATH_VARIABLE, postHandler::delete)
                                .build()
                );
    }

//    public RouterFunction<ServerResponse> replyRouter() {
//        return RouterFunctions
//                .nest(path(url("/reply")),
//                        RouterFunctions.route()
//                                .GET("", replyHandler::get)
//                                .GET(ID_PATH_VARIABLE, replyHandler::getOne)
//                                .POST("", replyHandler::create)
//                                .DELETE(ID_PATH_VARIABLE, replyHandler::delete)
//                                .build()
//                );
//    }

    private String url(String domain) {
        return "/api" + domain;
    }

}
