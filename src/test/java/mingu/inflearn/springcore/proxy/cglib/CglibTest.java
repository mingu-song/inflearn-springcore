package mingu.inflearn.springcore.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.proxy.cglib.code.TimeMethodInterceptor;
import mingu.inflearn.springcore.proxy.common.service.ConcreteService;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.call();
    }
}
