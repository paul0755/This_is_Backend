package com.example.study.global.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example..controller..*(..))")
    public Object logApi(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().toShortString();
        log.info("➡️ API 호출: {}", methodName);

        Object result = joinPoint.proceed();

        log.info("⬅️ API 응답: {} -> {}", methodName, result);

        return result;
    }
}
