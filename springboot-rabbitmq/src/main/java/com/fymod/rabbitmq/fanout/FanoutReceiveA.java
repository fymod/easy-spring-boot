package com.fymod.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiveA {

	@RabbitHandler
    public void process(String content) {
        System.out.println("receive A:" + content);
    }

}
