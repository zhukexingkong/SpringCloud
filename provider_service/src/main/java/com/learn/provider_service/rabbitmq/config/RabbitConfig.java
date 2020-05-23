package com.learn.provider_service.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    // 单条信息
    @Bean
    public Queue queueMessage(){
        return  new Queue("topic_message");
    }

    //多条信息
    @Bean
    public Queue queueMessages(){
        return  new Queue("topic_messages");
    }

    //广播queue
    @Bean
    public Queue fanoutQueue(){
        return  new Queue("fanout_messages");
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchage");
    }

    //Direct交换机的绑定
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        // queue 交换器 关键字
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    //Topic交换机的绑定
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    // 广播交换器
    @Bean
    BindingBuilder.TopicExchangeRoutingKeyConfigurer fanoutBindingExchangeMessage(Queue fanoutQueue, TopicExchange exchange){
        return BindingBuilder.bind(fanoutQueue).to(exchange);
    }
}