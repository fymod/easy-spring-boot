package com.fymod.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Autowired private Use use;
	
	@Test
	public void test1() {
		boolean bool = use.setTest("测试啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
		System.out.println(bool);
	}
	
	@Test
	public void test2() {
		String content = use.getTest("key1");
		System.out.println(content);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		content = use.getTest("key1");
		System.out.println(content);
	}

}
