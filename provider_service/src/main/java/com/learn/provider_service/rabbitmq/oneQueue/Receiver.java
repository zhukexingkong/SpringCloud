package com.learn.provider_service.rabbitmq.oneQueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
    //@RabbitListener表示对队列hello进行监听，@RabbitHandler指定对消息的处理方法；
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver: " + hello);
    }
}