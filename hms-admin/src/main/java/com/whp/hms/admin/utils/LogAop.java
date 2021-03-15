package com.whp.hms.admin.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    //切入点描述 这个是controller包的切入点
    @Pointcut("execution(public * com.whp.hms.admin.controller.PageController.*(..))")
    public void webLog() {
    }

    //在切入点的方法run之前要干的
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        //打印空行方便阅读
        //下面这个joinPoint.getSignature.getName()获取了方法名
        System.out.println();
        logger.info("--------------->aop日志打印ing<------------- : ");
        logger.info("---->请求URL : " + request.getRequestURL().toString());
        logger.info("---->HTTP方法 : " + request.getMethod());
        logger.info("---->方法名 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("返回体 : " + ret);
        System.out.println();
    }
}