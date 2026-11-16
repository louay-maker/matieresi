package tn.autoloc.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionHandlingAspect {

    // Advice @AfterThrowing : intercepte toutes les exceptions déclenchées par les méthodes du package service
    @AfterThrowing(pointcut = "execution(* tn.autoloc.autoloc.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String exceptionType = ex.getClass().getSimpleName();
        String errorMessage = ex.getMessage();

        log.error("[EXCEPTION ASPECT - @AfterThrowing] Exception dans [{}] | Type : {} | Message : {}",
                methodName, exceptionType, errorMessage);
    }
}
