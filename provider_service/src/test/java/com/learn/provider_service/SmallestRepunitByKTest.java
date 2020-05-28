package com.learn.provider_service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class SmallestRepunitByKTest {

    @Test
    public void smallestRepunitByK() {
        int K = 3;
        if (K % 2 == 0 || K % 5 == 0) {
            System.out.println("-1");
        }
        long digit = 1;
        for (int i = 1; ;i++) {
            if (digit % K == 0) {
                System.out.println("value: " + digit);
                System.out.println("length:" + i);
                break;
            }
            digit = (digit * 10 + 1);
        }
    }
}
