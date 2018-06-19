package com.fymod.activemq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fymod.activemq.domain.Student;
import com.fymod.activemq.queue.QueueSend;
import com.fymod.activemq.topic.TopicSend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActivemqApplicationTests {

	@Autowired private TopicSend topicSend;
	
	@Test
	public void topic() {
		List<Student> students = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Student student = new Student(10000 + i, "test" + i);
			students.add(student);
		}
		for(int i = 0; i < students.size(); i++) {
			topicSend.send(students.get(i));
		}
	}
	
	@Autowired private QueueSend queueSend;
	
	@Test
	public void queue() {
		List<Student> students = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Student student = new Student(10000 + i, "queueTest" + i);
			students.add(student);
		}
		for(int i = 0; i < students.size(); i++) {
			queueSend.send(students.get(i));
		}
	}

}
