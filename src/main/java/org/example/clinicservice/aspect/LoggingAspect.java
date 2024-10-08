package org.example.clinicservice.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * org.example.clinicservice.controller.*.*(..))")
    public void controllerLog(){

    }

    @Pointcut("execution(public * org.example.clinicservice.service.*.*(..))")
    public void serviceLog(){

    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}""",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());

    }

    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("""

                        Return value: {}
                        END OF REQUEST""",
                returnObject);
    }

    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }

}
