# Simple Quartz Scheduler

The goal is to learn scheduling, persistence, retries, and external calls without needing cloud infrastructure.

## Stack

- Spring Boot
- Quartz
- PostgreSQL Database
- Spring Web
- Spring Data JPA
- Actuator

## Local configuration

This application resolves datasource and server settings from local environment variables at runtime. Set these values locally before launching the app:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SERVER_PORT`


## Features

### Health Check Job

- Runs on a fixed interval and logs the health check response

### Todo API Poller

- Calls a Todo API endpoint using a random todo id between 1 and 20
- Logs a compact summary with the temperature and wind values returned by the current weather integration flow

### Dynamic Scheduling

- `POST /jobs`
- Create a new cron schedule at runtime

### Pause / Resume

- `PUT /jobs/{name}/pause`
- `PUT /jobs/{name}/resume`

### Delete Jobs

- Remove a scheduled job without restarting the application

### Misfire Testing

- Stop the application for a few minutes
- Restart it and observe how your chosen misfire policy behaves

### Clustering Experiment (Bonus)

- Run two instances of the application connected to the same PostgreSQL database
- Enable Quartz clustering
- Verify that only one instance executes each scheduled job
