package com.fymod.timed.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class ScheduledJob implements Job {

	@Override
    public void execute(JobExecutionContext context) { 
        System.out.println("定时任务" + new Date()); 
    } 
	
}
