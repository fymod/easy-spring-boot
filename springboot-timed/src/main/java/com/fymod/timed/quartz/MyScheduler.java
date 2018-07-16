package com.fymod.timed.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

	/**
	 * 已经在SchedulerListener.java进行了Bean注入
	 */
	@Autowired SchedulerFactoryBean schedulerFactoryBean;

	static Scheduler scheduler;

	public void scheduleJobs() throws SchedulerException {
		scheduler = schedulerFactoryBean.getScheduler();
		startJob(); 
	}

	public static void startJob() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job1", "group1").build();
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	public static void modifyJob(String cron) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		CronTrigger newTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, newTrigger);
	}
	public static String getJobStatus() throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
		return scheduler.getTriggerState(triggerKey).name();
	}
	public static void pauseJob() throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey("job1", "group1"));
	}
	public static void resumeJob() throws SchedulerException {
		scheduler.resumeJob(JobKey.jobKey("job1", "group1"));
	}

}
