package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class aspectDemo {
    @Pointcut("execution(public * com.example.demo.controller..*.*(..))")
    public void pointcut() {
        log.info("[切面处理] >> 使用注解 @Pointcut 定义切点位置");
    }
    /**
     * [@Before]：前置通知
     */
    @Before("pointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("[切面处理] >> 使用注解 @Before 调用了方法前置通知 ");

    }
}
