package com.infy.infyinterns.utility;

import com.infy.infyinterns.exception.InfyInternException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP-based logging aspect applied to every service method.
 *
 * @Around  — logs method entry, exit, and execution time.
 *            Real production use: detect slow DB calls (> 500 ms), N+1 queries, etc.
 *
 * @AfterThrowing — logs business exceptions at the service boundary
 *                  before they bubble up to ExceptionControllerAdvice.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    /* ── Execution-time logging for all service methods ── */
    @Around("execution(* com.infy.infyinterns.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        long   start  = System.currentTimeMillis();

        LOGGER.info(">> Entering : " + method);
        try {
            Object result = joinPoint.proceed();
            long elapsed  = System.currentTimeMillis() - start;
            LOGGER.info("<< Exiting  : " + method + " | took " + elapsed + " ms");
            return result;
        } catch (Exception ex) {
            LOGGER.error("!! Exception in " + method + " : " + ex.getMessage());
            throw ex;
        }
    }

    /* ── Dedicated logging for InfyInternException ── */
    @AfterThrowing(
        pointcut = "execution(* com.infy.infyinterns.service.*.*(..))",
        throwing  = "exception")
    public void logServiceException(InfyInternException exception) {
        LOGGER.error("Service exception: " + exception.getMessage(), exception);
    }
}