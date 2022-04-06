package mingu.inflearn.springcore;

import mingu.inflearn.springcore.trace.logtrace.FieldLogTrace;
import mingu.inflearn.springcore.trace.logtrace.LogTrace;
import mingu.inflearn.springcore.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
