package com.fymod.activemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fymod.activemq.domain.Student;

@Component
public class TopicReceive {

    @JmsListener(destination = "topic")
    public void receiveTopic(Student student) {
        System.out.println("topic : " + student.getName());
    }

}
