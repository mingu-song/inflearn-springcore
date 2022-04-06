package mingu.inflearn.springcore.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.springcore.trace.threadlocal.code.FieldService;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private final FieldService fieldService = new FieldService();

    @Test
    void fiend() {
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        Thread threadB = new Thread(userB);
        threadA.setName("thread-A");
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000); // 동시성 문제 x
        sleep(100); // 동시성 문제 o
        threadB.start();

        sleep(3000); // 메인 쓰레드 대기
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
