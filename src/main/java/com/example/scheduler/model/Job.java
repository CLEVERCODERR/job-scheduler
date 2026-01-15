package com.example.scheduler.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Job {
    private String id;
    private String name;
    private JobStatus status;
    private int retryCount;
    private String result;
    private LocalDateTime submittedAt;

    public Job(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.status = JobStatus.PENDING;
        this.retryCount = 0;
        this.submittedAt = LocalDateTime.now();
    }
}
