package com.yankaizhang.link.aspect;

import com.yankaizhang.spring.aop.annotation.*;
import com.yankaizhang.spring.aop.aspect.JoinPoint;
import com.yankaizhang.spring.context.annotation.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @PointCut("com.yankaizhang.link.controller.*.*(*)")
    public void pt(){}

    @Before
    public void before(JoinPoint joinPoint){
        log.info("进入方法 : {}, 参数 : {}", joinPoint.getMethod().getName(), joinPoint.getArguments());
    }

    @AfterReturning
    public void after(JoinPoint joinPoint, Object returnValue){
        log.info("离开方法 : {}, 返回值 : {}", joinPoint.getMethod().getName(), returnValue);
    }

    @AfterThrowing
    public void error(JoinPoint joinPoint, Throwable ex){
        log.error("执行错误 : {}, 原因 : {}", joinPoint.getMethod().getName(), ex.getMessage());
    }
}
