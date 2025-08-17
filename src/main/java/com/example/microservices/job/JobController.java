package com.example.microservices.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<Job>>findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        String response = jobService.createJob(job);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Job deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated) {
            return new ResponseEntity<>("Job updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
        }
    }

}
