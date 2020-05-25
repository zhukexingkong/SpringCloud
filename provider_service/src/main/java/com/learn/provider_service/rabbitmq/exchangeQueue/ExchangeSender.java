package com.learn.provider_service.rabbitmq.exchangeQueue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeSender {
    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public void send(){
        // 交换器  绑定关键字 内容
        System.out.println("topic.message Queue:" + "topic_message");
        System.out.println("topic.messages Queue:" + "topic_messages");
        this.rabbitmqTemplate.convertAndSend("exchage","topic.message","topic_message");
        this.rabbitmqTemplate.convertAndSend("exchage","topic.messages","topic_messages");
        System.out.println("fanout_messages Queue:" + "fanout_messages");
        this.rabbitmqTemplate.convertAndSend("fanout_messages", "fanout_messages");
    }
}
