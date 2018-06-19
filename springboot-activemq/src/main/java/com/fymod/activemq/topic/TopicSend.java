package com.fymod.activemq.topic;

import javax.jms.Destination;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fymod.activemq.domain.Student;

@Component
public class TopicSend {

    @Autowired private JmsTemplate jmsTemplate;

    @Autowired private Topic topic;
    
    public void send2(Student student) {
    		jmsTemplate.convertAndSend(topic, student);
    }

    public void send(Student student){
    		Destination destination = new ActiveMQTopic("topic");
    		jmsTemplate.convertAndSend(destination, student);
    }
}

