package hyun.dashboard.publisher.exception;

import hyun.dashboard.publisher.common.exception.GlobalExceptionController;
import hyun.dashboard.publisher.common.aop.ExceptionLogAspect;
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