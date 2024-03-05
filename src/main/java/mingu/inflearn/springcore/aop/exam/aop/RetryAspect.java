package mingu.inflearn.springcore.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.aop.exam.annotation.Retry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {}, retry = {}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHandler = new RuntimeException("Default");

        for (int retryCnt = 1; retryCnt <= maxRetry; retryCnt++) {
            try {
                log.info("[retry] try count = {} / {}", retryCnt, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHandler = e;
            }
        }
        throw exceptionHandler;
    }
}
