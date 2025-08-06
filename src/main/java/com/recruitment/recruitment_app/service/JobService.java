package com.recruitment.recruitment_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import com.recruitment.recruitment_app.model.Job;
import com.recruitment.recruitment_app.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public ResponseEntity<Job> getJobById (Long jobId){
        return jobRepository.findById(jobId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobRepository.findById(id)
        .map(existingJob -> {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setlocation(updatedJob.getlocation());
           // existingJob.setskillsrequired(updatedJob.getskillsrequired());
            existingJob.setSalary(updatedJob.getSalary());
            Job savedJob = jobRepository.save(existingJob);
            return ResponseEntity.ok(savedJob);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return ResponseEntity.ok("Job deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
        }
    }
    public List<Job> filterJobsBySkill(String skill) {
    return jobRepository.findAll().stream()
            .filter(job -> job.getskillsrequired() != null && job.getskillsrequired().contains(skill))
            .collect(Collectors.toList());
}
    
}
