package com.learn.provider_service.rabbitmq.exchangeQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic_message")
public class ExchangeReceiver1 {

    @RabbitHandler
    public void process(String hello){
        System.out.println("ExchangeReceiver1: " + hello);
    }
}
