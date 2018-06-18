package com.fymod.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fymod.rabbitmq.direct.DirectSender;
import com.fymod.rabbitmq.direct.DirectSender2;
import com.fymod.rabbitmq.domain.Student;
import com.fymod.rabbitmq.fanout.FanoutSender;
import com.fymod.rabbitmq.topic.TopicSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {
	
	@Autowired private DirectSender directSender;
	@Autowired private DirectSender2 directSender2;

	@Test
	public void direct() {
		List<Student> students = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Student student = new Student(10000 + i, "姓名" + (i+1));
			students.add(student);
		}
		for(int i = 0; i < students.size(); i++) {
			directSender.send(students.get(i));
			directSender2.send(students.get(i));
		}
	}
	
	@Autowired TopicSender topicSender;
	
	@Test
	public void topic() {
		// 发送send2，两个接受者都能收到消息
		for(int i = 0; i < 10; i++) {
			topicSender.send1();
		}
		// 发送send2，只有接受者2能收到
//		for(int i = 0; i < 10; i++) {
//			topicSender.send2();
//		}
	}
	
	@Autowired FanoutSender fanoutSender;
	
	@Test
	public void fanout() {
		fanoutSender.send();
	}

}
