//package mingu.inflearn.springcore.config.v6_aop;
//
//import mingu.inflearn.springcore.config.AppV1Config;
//import mingu.inflearn.springcore.config.AppV2Config;
//import mingu.inflearn.springcore.config.v6_aop.aspect.LogTraceAspect;
//import mingu.inflearn.springcore.trace.logtrace.LogTrace;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//@Configuration
//@Import({AppV1Config.class, AppV2Config.class})
//public class AopConfig {
//
//    @Bean
//    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
//        return new LogTraceAspect(logTrace);
//    }
//}
