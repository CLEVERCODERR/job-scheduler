# Java Distributed Job Scheduler (Backend)

I've created a multithreaded backend job scheduling system built with Java and Spring Boot. This project demonstrates backend concurrency, REST API design, and asynchronous job execution.

## Features
- Submit jobs via REST API
- Track job status: PENDING, RUNNING, COMPLETED, FAILED, CANCELLED
- Asynchronous execution using `ScheduledExecutorService`
- Thread-safe in-memory storage (`ConcurrentHashMap`)
- Retry logic and simulated job execution
- Cancel pending jobs

## Technologies Used
- Language: Java
- Framework: Spring Boot
- Concurrency: Java Executors, Threads
- REST API development
- Data persistence: in-memory storage


