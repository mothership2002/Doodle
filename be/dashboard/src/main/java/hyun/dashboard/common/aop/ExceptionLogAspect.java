package hyun.dashboard.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j(topic = "ExceptionLog")
@Aspect
public class ExceptionLogAspect {

    @Around("execution(* com.example.webflux.common.exception.*.*Log(..))")
    public Object logException(ProceedingJoinPoint pjp) throws Throwable {
        Arrays.stream(pjp.getArgs()).filter(arg -> arg instanceof Throwable)
                .forEach(arg -> log.error("{}", pjp.getSignature().getName(), (Throwable) arg));
        return pjp.proceed();
    }
}
