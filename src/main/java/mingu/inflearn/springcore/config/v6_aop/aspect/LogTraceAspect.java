package mingu.inflearn.springcore.config.v6_aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.trace.TraceStatus;
import mingu.inflearn.springcore.trace.logtrace.LogTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@RequiredArgsConstructor
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* mingu.inflearn.springcore.proxyapp..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
//        log.info("target={}", joinPoint.getTarget()); //실제 호출 대상
//        log.info("getArgs={}", joinPoint.getArgs()); //전달인자
//        log.info("getSignature={}", joinPoint.getSignature()); //join point 시그니처
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);
            //로직 호출
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
