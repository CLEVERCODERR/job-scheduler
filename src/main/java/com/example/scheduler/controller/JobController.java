package com.example.scheduler.controller;

import com.example.scheduler.model.Job;
import com.example.scheduler.store.JobStore;
import com.example.scheduler.service.JobSchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobSchedulerService scheduler;
    private final JobStore store;

    public JobController(JobSchedulerService scheduler, JobStore store) {
        this.scheduler = scheduler;
        this.store = store;
    }

    @PostMapping
    public ResponseEntity<Job> submitJob(@RequestParam String name) {
        Job job = scheduler.submitJob(name);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        Job job = store.getJob(id);
        if (job == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(job);
    }

    @GetMapping
    public Collection<Job> getAllJobs() {
        return store.getAllJobs();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> cancelJob(@PathVariable String id) {
        Job job = scheduler.cancelJob(id);
        if (job == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(job);
    }
}
