package at.lh.importservice.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    private static final Map<Class<?>, Logger> LOGGER_MAP = new HashMap<>();

    @Around("execution(* at.lh.importservice.controller.*.*(..))")
    public Object logControllerExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = getLogger(joinPoint.getTarget().getClass());
        logger.info("started {}() function", joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        logger.info("{}() executed in {}ms", joinPoint.getSignature().getName(), executionTime);

        return proceed;
    }

    private Logger getLogger(Class<?> clazz) {
        if (!LOGGER_MAP.containsKey(clazz)) {
            LOGGER_MAP.put(clazz, LoggerFactory.getLogger(clazz));
        }

        return LOGGER_MAP.get(clazz);
    }
}
