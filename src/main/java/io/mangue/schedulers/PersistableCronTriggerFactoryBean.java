package io.mangue.schedulers;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import java.text.ParseException;

public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {
	String JOB_DETAIL_KEY = "jobDetail";

	@Override
	public void afterPropertiesSet() throws ParseException {
		super.afterPropertiesSet();
		//Remove the JobDetail element
		getJobDataMap().remove(JOB_DETAIL_KEY);
	}
}