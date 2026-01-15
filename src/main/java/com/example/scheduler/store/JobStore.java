package com.example.scheduler.store;

import com.example.scheduler.model.Job;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JobStore {
    private final ConcurrentHashMap<String, Job> jobs = new ConcurrentHashMap<>();

    public void addJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public Job getJob(String id) {
        return jobs.get(id);
    }

    public Collection<Job> getAllJobs() {
        return jobs.values();
    }

    public void removeJob(String id) {
        jobs.remove(id);
    }
}
