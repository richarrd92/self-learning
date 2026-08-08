package com.example.automation_scheduler.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService {

    @Value("${todos.api.url}")
    private String TODO_API_URL;

    private final RestClient restClient;

    @Override
    public String healthCheck() {
        return "Health check successful!";
    }

    @Override
    public String callTodoApi() {
        int todoId = 1 + (int) (Math.random() * 20);
        return restClient.get()
                .uri(TODO_API_URL + todoId)
                .retrieve()
                .body(String.class);
    }
}
