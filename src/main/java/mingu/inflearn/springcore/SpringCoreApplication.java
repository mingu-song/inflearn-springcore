package mingu.inflearn.springcore;

import mingu.inflearn.springcore.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import mingu.inflearn.springcore.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import mingu.inflearn.springcore.config.v3_proxyfactory.ProxyFactoryConfigV1;
import mingu.inflearn.springcore.config.v3_proxyfactory.ProxyFactoryConfigV2;
import mingu.inflearn.springcore.config.v4_postprocessor.BeanPostProcessorConfig;
import mingu.inflearn.springcore.config.v5_autoproxy.AutoProxyConfig;
import mingu.inflearn.springcore.config.v6_aop.AopConfig;
import mingu.inflearn.springcore.trace.logtrace.LogTrace;
import mingu.inflearn.springcore.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "mingu.inflearn.springcore.proxyapp") // v3만 빈으로 등록
public class SpringCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }

}
