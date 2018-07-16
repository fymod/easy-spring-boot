package com.fymod.timed.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 当容器初始化完成之后，需要处理一些操作。
 * 比如一些数据的加载、初始化缓存、特定任务的注册等等。
 * 这个时候我们就可以使用Spring提供的ApplicationListener来进行操作。
 * 首先，需要实现ApplicationListener接口并实现onApplicationEvent方法。
 * 把需要处理的操作放在onApplicationEvent中进行处理。
 * 通过@Configuration配置来进行实例化
 */
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public MyScheduler myScheduler;

	/**
	 * 会自动调用这个方法。
	 * myScheduler.scheduleJobs() 启动定时任务
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			myScheduler.scheduleJobs();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个和ApplicationListener没有什么关系。
	 * 主要是为了注入一个对象，也可以在其他有@Configuration的类中。
	 * 必须有@Bean
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}
}
