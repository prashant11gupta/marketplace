package com.intuit.cron;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Prashant
 */
public class CronScheduler{

	public CronScheduler() throws Exception {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();

		JobDetail job = newJob(CheckStatus.class).withIdentity("job1", "group1")
				.build();
		String exp = "0 /1 * * * ?"; // Fire every 5 mins
		Trigger trigger = newTrigger()
			      .withIdentity("trigger1", "group1")
			      .startNow()
			      .withSchedule(CronScheduleBuilder.cronSchedule(exp))
			      .build();

		scheduler.scheduleJob(job, trigger);
	}

}