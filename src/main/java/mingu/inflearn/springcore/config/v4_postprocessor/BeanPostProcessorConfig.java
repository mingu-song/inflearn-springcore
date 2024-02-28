package mingu.inflearn.springcore.config.v4_postprocessor;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.config.AppV1Config;
import mingu.inflearn.springcore.config.AppV2Config;
import mingu.inflearn.springcore.config.v3_proxyfactory.advice.LogTraceAdvice;
import mingu.inflearn.springcore.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import mingu.inflearn.springcore.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor("mingu.inflearn.springcore.proxyapp", getAdvisor(logTrace));
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
