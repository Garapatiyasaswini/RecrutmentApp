package com.recruitment.recruitment_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recruitment.recruitment_app.model.Interview;
import com.recruitment.recruitment_app.service.InterviewService;

import java.util.List;


@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping("/schedule")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public Interview schedule(@RequestBody Interview interview) {
        return interviewService.scheduleInterview(interview);
    }

    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Interview> allInterviews() {
        return interviewService.getAllInterviews();
    }
@GetMapping("/candidate/{id}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<Interview> getForCandidate(@PathVariable long id) {
        return interviewService.getByCandidateId(id);
    }
@PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public Interview updateStatus(@PathVariable long id, @RequestParam String status) {
        return interviewService.updateStatus(id, status);
    }
}

