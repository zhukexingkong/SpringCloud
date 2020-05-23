package com.learn.provider_service.rabbitmq.exchangeQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout_messages")
public class FanoutReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("fanout_messages: " + hello);
    }
}
