package com.fymod.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fymod.rabbitmq.domain.Student;

@Component
public class DirectSender {

    @Autowired private AmqpTemplate rabbitTemplate;

    public void send(Student student) {
        System.out.println("send:" + student.getNumber());
        this.rabbitTemplate.convertAndSend("test1", student);
    }

}
