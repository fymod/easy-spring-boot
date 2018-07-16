package com.fymod.timed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务需要添加一个注解 @EnableScheduling
 */
@EnableScheduling
@SpringBootApplication
public class SpringbootTimedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTimedApplication.class, args);
	}
	
	/**
	 * 固定频率执行的，可以直接使用fixedRate，单位是毫秒
	 */
	@Scheduled(fixedRate = 5000)
	public void schedule1CronTrigger() {
		System.out.println("======5s运行一次，测试用的=======");
	}
	
	/**
	 * 同时支持cron表达式
	 */
	@Scheduled(cron = "*/5 * * * * ?")
	public void schedule2CronTrigger() {
		System.out.println("5s运行一次，测试用的");
	}
	
}
