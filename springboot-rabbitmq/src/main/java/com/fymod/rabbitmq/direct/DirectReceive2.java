package com.fymod.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fymod.rabbitmq.domain.Student;

@Component
@RabbitListener(queues = "test1")
public class DirectReceive2 {

    @RabbitHandler
    public void process(Student student) {
        System.out.println("receive2:" + student.getName());
    }

}
