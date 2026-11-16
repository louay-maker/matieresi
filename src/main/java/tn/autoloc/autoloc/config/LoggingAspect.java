package tn.autoloc.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Advice @Before : s'exécute avant chaque méthode de la couche service
    @Before("execution(* tn.autoloc.autoloc.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("[LOGGING ASPECT - @Before] Méthode appelée : {} | Paramètres : {}",
                methodName, Arrays.toString(args));
    }

    // Advice @After : s'exécute après chaque méthode de la couche service
    @After("execution(* tn.autoloc.autoloc.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("[LOGGING ASPECT - @After] Fin d'exécution de la méthode : {}", methodName);
    }
}
