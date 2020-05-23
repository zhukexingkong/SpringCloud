package com.learn.provider_service.rabbitmq.exchangeQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic_messages")
public class ExchangeReceiver2 {

    @RabbitHandler
    public void process(String hello){
        System.out.println("ExchangeReceiver2: " + hello);
    }
}
