package com.fymod.timed.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
	
	@GetMapping("/modify")
	public void modify() throws SchedulerException {
		MyScheduler.modifyJob("0/1 * * * * ?");
	}
	
	@GetMapping("/status")
	public String status() throws SchedulerException {
		return MyScheduler.getJobStatus();
	}
	
	@GetMapping("/pause")
	public void pause() throws SchedulerException {
		MyScheduler.pauseJob();
	}
	
	@GetMapping("/resume")
	public void resume() throws SchedulerException {
		MyScheduler.resumeJob();
	}
	
	
	@Autowired private ConfigRepository repository;
	
	@GetMapping("/modify2")
	public void modify2() throws SchedulerException {
		String cron = repository.findByTypes(100).get(0).getCron();
		MyScheduler.modifyJob(cron);
	}
	
}
