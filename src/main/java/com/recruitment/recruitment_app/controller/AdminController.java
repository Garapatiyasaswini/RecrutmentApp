package com.recruitment.recruitment_app.controller;

import com.recruitment.recruitment_app.model.Job;
import com.recruitment.recruitment_app.repository.JobRepository;

//import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
   public String adminDashboard() {
       return "Welcome ADMIN! This is your dashboard";
   }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('ADMIN')")
   public List<Job> getAllJobs() {
       return jobRepository.findAll();
   }

   @GetMapping("/jobs/{id}")
   public ResponseEntity<Job> getJobById(@PathVariable Long id) {
    return jobRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
}

 @PostMapping("/jobs/create")
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        return jobRepository.findById(id)
        .map(existingJob -> {
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setlocation(updatedJob.getlocation());
            //existingJob.setskillsrequired(updatedJob.getskillsrequired());
            Job savedJob = jobRepository.save(existingJob);
            return ResponseEntity.ok(savedJob);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("jobs/{id}")
public ResponseEntity<String> deleteJob(@PathVariable Long id) {
    if (jobRepository.existsById(id)) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok("Job deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
    }
}
    
}
