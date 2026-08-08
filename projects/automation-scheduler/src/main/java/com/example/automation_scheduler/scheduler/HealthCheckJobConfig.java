package com.example.automation_scheduler.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckJobConfig {

    @Bean
    public JobDetail healthCheckJobDetail() {
        return JobBuilder.newJob(HealthCheckJob.class)
                .withIdentity("healthCheckJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger healthCheckJobTrigger(JobDetail healthCheckJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(healthCheckJobDetail)
                .withIdentity("healthCheckTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever())
                .build();
    }
}
