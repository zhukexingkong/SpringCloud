package com.learn.provider_service;

import com.learn.provider_service.rabbitmq.exchangeQueue.ExchangeSender;
import com.learn.provider_service.rabbitmq.oneQueue.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class HelloApplicationTests {

    @Autowired
    private Sender sender;

    @Autowired
    private ExchangeSender exchangeSender;

    @Test
    public void hello(){
        sender.send();
    }

    @Test
    public void message(){
        exchangeSender.send();
    }
}
