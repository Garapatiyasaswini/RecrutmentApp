package com.recruitment.recruitment_app.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String resumeLink;

    // This assumes one candidate applies for one job.
    // If a candidate can apply to multiple jobs, you need a separate JobApplication table.

    @ManyToOne
    @JoinColumn(name = "job_id")  // Foreign key to Job
    private Job job;
     @ElementCollection
    private List<String> skills;

    // Constructors
    public Candidate() {}

    public Candidate(String name, String email, String phoneNumber, String resumeLink, Job job, List<String> skills) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resumeLink = resumeLink;
        this.job = job;
        this.skills = skills;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<String> getSkills() {
        return skills;
    }

      public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    
}