package com.example.automation_scheduler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.automation_scheduler.service.RestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/automation-scheduler")
public class Controller {

    private final RestService service;

    @GetMapping("/health-check")
    public String healthCheck() {
        return service.healthCheck();
    }
}