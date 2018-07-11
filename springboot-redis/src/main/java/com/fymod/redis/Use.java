package com.fymod.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Use {

	@Autowired private StringRedisTemplate template;
	
	// 和StringRedisTemplate操作类似，只是保存和读取类型不同
//	@Autowired private RedisTemplate redisTemplate;
//	public void test() {
//		redisTemplate.opsForValue();//操作字符串
//		redisTemplate.opsForHash();//操作hash
//		redisTemplate.opsForList();//操作list
//		redisTemplate.opsForSet();//操作set
//		redisTemplate.opsForZSet();//操作有序set
//	}
	
	public boolean setTest(String content) {
		template.opsForValue().set("key1", content, 30, TimeUnit.SECONDS);
		return true;
	}
	
	public String getTest(String key) {
		return template.opsForValue().get(key);
	}
	
}
