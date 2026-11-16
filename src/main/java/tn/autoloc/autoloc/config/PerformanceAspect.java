package tn.autoloc.autoloc.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    // Advice @Around : intercepte toutes les méthodes du package service pour mesurer leurs performances
    @Around("execution(* tn.autoloc.autoloc.service.*.*(..))")
    public Object profileMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        long start = System.currentTimeMillis();

        // Exécution de la méthode cible
        Object result = proceedingJoinPoint.proceed();

        // Calcul du temps d'exécution
        long elapsedTime = System.currentTimeMillis() - start;

        // Affichage du temps d'exécution en millisecondes
        log.info("[PERFORMANCE ASPECT - @Around] Méthode [{}] exécutée en : {} ms", methodName, elapsedTime);

        // Signalement des méthodes dont le temps dépasse 1000 ms
        if (elapsedTime > 1000) {
            log.warn("[PERFORMANCE ASPECT - ALERTE] Temps d'exécution excessif pour [{}] : {} ms (> 1000 ms)",
                    methodName, elapsedTime);
        }

        // Retourner le résultat de la méthode interceptée
        return result;
    }
}
