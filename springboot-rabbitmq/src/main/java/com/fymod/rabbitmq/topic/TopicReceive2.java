package com.fymod.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test2.messages")
public class TopicReceive2 {

	@RabbitHandler
    public void process(String content) {
        System.out.println("receive2:" + content);
    }

}
