package com.example.webflux.common.module;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.function.Consumer;

@Deprecated
public class HeaderUtils {

    public static Consumer<HttpHeaders> getHeader() {
        return headers -> {
            headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        };
    }
}
