package com.example.automation_scheduler.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoApiJobConfig {

    @Bean
    public JobDetail todoApiJobDetail() {
        return JobBuilder.newJob(TodoApiJob.class)
                .withIdentity("todoApiJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger todoApiJobTrigger(JobDetail todoApiJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(todoApiJobDetail)
                .withIdentity("todoApiTrigger")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(30)
                                .repeatForever())
                .build();
    }
}
