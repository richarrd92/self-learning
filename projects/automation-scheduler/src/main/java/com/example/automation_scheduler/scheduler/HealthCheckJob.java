package com.example.automation_scheduler.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.automation_scheduler.service.RestService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class HealthCheckJob implements Job {

    private final RestService restService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String healthCheckResponse = restService.healthCheck();
        log.info("{}", healthCheckResponse);
    }
}
