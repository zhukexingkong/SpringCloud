package com.learn.provider_service;

import com.learn.provider_service.lockDemo.LockDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class LockDemoTest {
    @Test
    public void testLockDemo() {

        LockDemo td1 = new LockDemo();
        LockDemo td2 = new LockDemo();
        td1.flag = 1;
        td2.flag = 0;
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //td2的run()可能在td1的run()之前运行
        new Thread(td1).start();
        new Thread(td2).start();

    }
}
