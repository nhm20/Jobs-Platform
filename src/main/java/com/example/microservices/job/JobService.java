package com.example.microservices.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }
    public String createJob(Job job) {
        jobRepository.save(job);
        return "Job created successfully!";
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public boolean deleteJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return false;
        }
        else {
            jobRepository.delete(job);
            return true;
        }
    }

    public boolean updateJob(Long id, Job updatedJob) {
        Job existingJob = jobRepository.findById(id).orElse(null);
        if (existingJob == null) {
            return false;
        } else {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setLocation(updatedJob.getLocation());
            jobRepository.save(existingJob);
            return true;
        }
    }
}
