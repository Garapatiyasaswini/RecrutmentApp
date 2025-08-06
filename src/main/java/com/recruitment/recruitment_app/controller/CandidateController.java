package com.recruitment.recruitment_app.controller;
import com.recruitment.recruitment_app.model.Candidate;
import com.recruitment.recruitment_app.model.Job;
import com.recruitment.recruitment_app.service.CandidateJobService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateJobService candidateJobService;

    @GetMapping("/job")
    public ResponseEntity<List<Job>> listJobs() {
        return ResponseEntity.ok(candidateJobService.getAllJobs());
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {
        return candidateJobService.getJobById(id);
    }
    // Create a new candidate (apply for job)
    @PostMapping()
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        System.out.println("Start");
        Candidate savedCandidate = candidateJobService.createCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }

    // Get all candidates
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateJobService.getAllCandidate();
        return ResponseEntity.ok(candidates);
    }

    // Get candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return candidateJobService.getCandidateById(id);
    }

    // Update candidate by ID
    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {
        return candidateJobService.updateCandidate(id, updatedCandidate);
    }

    // Get all jobs (for viewing available jobs)
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = candidateJobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    // Get job by ID
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return candidateJobService.getJobById(id);
    }
    
    @GetMapping("/match/{id}")
@PreAuthorize("hasRole('CANDIDATE')")
public List<Job> getMatchingJobs(@PathVariable Long id) {
    Candidate candidate = candidateJobService.getCandidateById(id).getBody();
    return candidateJobService.matchJobsForCandidate(candidate);
}
}


    

