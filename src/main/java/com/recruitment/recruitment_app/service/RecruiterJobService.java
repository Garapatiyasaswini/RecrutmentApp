package com.recruitment.recruitment_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.recruitment.recruitment_app.model.Job;
import com.recruitment.recruitment_app.repository.JobRepository;
// import com.recruitment.recruitment_app.repository.UserRepository;

@Service
public class RecruiterJobService {
    @Autowired
    private JobRepository jobRepository;
    // private UserRepository userRepository;

   public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getRecruiterJobs() {
        return jobRepository.findAll();
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
    
}
