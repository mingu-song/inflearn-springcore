package mingu.inflearn.springcore.aop.member;

import mingu.inflearn.springcore.aop.member.annotation.ClassAop;
import mingu.inflearn.springcore.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String name) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
