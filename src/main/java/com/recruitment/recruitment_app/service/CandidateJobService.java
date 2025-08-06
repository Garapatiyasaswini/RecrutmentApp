package com.recruitment.recruitment_app.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.recruitment.recruitment_app.model.Candidate;
import com.recruitment.recruitment_app.model.Job;
import com.recruitment.recruitment_app.repository.CandidateRepository;
import com.recruitment.recruitment_app.repository.JobRepository;

@Service
public class CandidateJobService {
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

     // @Autowired
    public CandidateJobService(CandidateRepository candidateRepository,
                               JobRepository jobRepository) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
    }

    public Candidate createCandidate(Candidate candidate) {
        System.out.println("candidate service");
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidate() {
       return candidateRepository.findAll();
   }

   public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
    return candidateRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
}

 public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
        return candidateRepository.findById(id)
        .map(existingCandidate -> {
            existingCandidate.setName(updatedCandidate.getName());
            existingCandidate.setEmail(updatedCandidate.getEmail());
            existingCandidate.setPhoneNumber(updatedCandidate.getPhoneNumber());
            existingCandidate.setResumeLink(updatedCandidate.getResumeLink());
            existingCandidate.setJob(updatedCandidate.getJob());
            Candidate savedCandidate = candidateRepository.save(existingCandidate);
            return ResponseEntity.ok(savedCandidate);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

   public List<Job> getAllJobs() {
       return jobRepository.findAll();
   }

   public ResponseEntity<Job> getJobById(@PathVariable Long id) {
    return jobRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
}
public List<Job> matchJobsForCandidate(Candidate candidate) {
    List<Job> allJobs = jobRepository.findAll();
    return allJobs.stream()
            .filter(job -> job.getskillsrequired() != null &&
                    !Collections.disjoint(candidate.getSkills(), job.getskillsrequired()))
            .collect(Collectors.toList());
}
}
