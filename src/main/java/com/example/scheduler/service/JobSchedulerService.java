package com.example.scheduler.service;

import com.example.scheduler.model.Job;
import com.example.scheduler.model.JobStatus;
import com.example.scheduler.store.JobStore;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class JobSchedulerService {

    private final JobStore jobStore;
    private final ScheduledExecutorService executor;

    public JobSchedulerService(JobStore jobStore) {
        this.jobStore = jobStore;
        this.executor = Executors.newScheduledThreadPool(5);
    }

    public Job submitJob(String name) {
        Job job = new Job(name);
        jobStore.addJob(job);

        executor.submit(() -> runJob(job));
        return job;
    }

    private void runJob(Job job) {
        job.setStatus(JobStatus.RUNNING);
        try {
            // Simulate work
            Thread.sleep((long) (Math.random() * 5000) + 1000);

            // Randomly succeed or fail
            if (Math.random() < 0.8) {
                job.setStatus(JobStatus.COMPLETED);
                job.setResult("Job " + job.getName() + " completed successfully.");
            } else {
                job.setStatus(JobStatus.FAILED);
                job.setResult("Job " + job.getName() + " failed.");
            }
        } catch (InterruptedException e) {
            job.setStatus(JobStatus.FAILED);
            job.setResult("Job interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public Job cancelJob(String id) {
        Job job = jobStore.getJob(id);
        if (job != null && job.getStatus() == JobStatus.PENDING) {
            job.setStatus(JobStatus.CANCELLED);
        }
        return job;
    }
}
