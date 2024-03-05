package mingu.inflearn.springcore.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.aop.member.MemberService;
import mingu.inflearn.springcore.aop.member.MemberServiceImpl;
import mingu.inflearn.springcore.aop.proxyvs.code.ProxyDIAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK 동적 프록시, DI 예외 발생
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB 프록시, 성공
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService; // JDK o, CGLIB o

    @Autowired
    MemberServiceImpl memberServiceImpl; // JDK x, CGLIB o

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
