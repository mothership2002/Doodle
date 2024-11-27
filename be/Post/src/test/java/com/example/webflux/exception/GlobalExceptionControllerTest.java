package com.example.webflux.exception;

import com.example.webflux.common.exception.GlobalExceptionController;
import com.example.webflux.common.aop.ExceptionLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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