package com.fymod.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    final static String message = "test2.message";
    final static String messages = "test2.messages";

    @Bean
    public Queue testMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue testMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue testMessage, TopicExchange exchange) {
        return BindingBuilder.bind(testMessage).to(exchange).with("test2.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue testMessages, TopicExchange exchange) {
        return BindingBuilder.bind(testMessages).to(exchange).with("test2.#");
    }
    
}
