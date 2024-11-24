package com.example.webflux.exception;

import com.example.webflux.exception.aop.ExceptionLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalExceptionControllerTest {

    @Autowired
    GlobalExceptionController controller;
    @Autowired
    ExceptionLogAspect aspect;

    @Test
    void test() {
        controller.handleRuntimeException(new RuntimeException("hello"));
    }

    @Test
    void test2() {
        controller.handleException(new Exception("hello"));
    }
}