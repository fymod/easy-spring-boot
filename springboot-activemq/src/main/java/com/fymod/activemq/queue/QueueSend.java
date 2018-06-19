package com.fymod.activemq.queue;

import javax.jms.Destination;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fymod.activemq.domain.Student;

@Component
public class QueueSend {

    @Autowired private JmsTemplate jmsTemplate;

    @Autowired private Queue queue;

    public void send2(Student student){
        jmsTemplate.convertAndSend(queue, student);
    }
    
    public void send(Student student){
    		Destination destination = new ActiveMQQueue("queue");
    		jmsTemplate.convertAndSend(destination, student);
    }
}

