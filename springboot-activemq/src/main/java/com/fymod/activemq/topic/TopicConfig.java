package com.fymod.activemq.topic;

import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("topic");
    }
}
