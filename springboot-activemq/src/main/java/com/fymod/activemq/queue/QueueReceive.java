package com.fymod.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fymod.activemq.domain.Student;

@Component
public class QueueReceive {

    @JmsListener(destination = "queue")
    public void receiveQueue(Student student) {
    		System.out.println("queue : " + student.getName());
    }
    
}
