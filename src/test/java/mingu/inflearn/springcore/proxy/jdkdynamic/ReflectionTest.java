package mingu.inflearn.springcore.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> hello = Class.forName("mingu.inflearn.springcore.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodA = hello.getMethod("callA");
        Object resultA = methodA.invoke(target);
        log.info("resultA = {}", resultA);
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> hello = Class.forName("mingu.inflearn.springcore.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method methodA = hello.getMethod("callB");
        dynamicCall(methodA, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object resultA = method.invoke(target);
        log.info("result = {}", resultA);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
