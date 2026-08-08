package com.example.automation_scheduler.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.example.automation_scheduler.service.RestService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class TodoApiJob implements Job {
    private final RestService restService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Todo API job started");
        try {
            String todoApiResponse = restService.callTodoApi();
            log.info("Todo API summary: {}", todoApiResponse);
        } catch (Exception ex) {
            log.error("Todo API job failed", ex);
            throw new JobExecutionException("Todo API call failed", ex);
        }
    }
}
