package mingu.inflearn.springcore.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.aop.member.MemberService;
import mingu.inflearn.springcore.aop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        // 인터페이스로 캐스팅
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("proxy class = {}", memberServiceProxy.getClass());

        Assertions.assertThatThrownBy(() -> {
            MemberServiceImpl casting = (MemberServiceImpl) memberServiceProxy;
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 프록시

        // 인터페이스로 캐스팅
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("proxy class = {}", memberServiceProxy.getClass());

        // 구체클래스로 캐스팅
        MemberServiceImpl casting = (MemberServiceImpl) memberServiceProxy;
    }
}
