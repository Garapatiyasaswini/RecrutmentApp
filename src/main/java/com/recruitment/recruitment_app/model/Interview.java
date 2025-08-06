package com.recruitment.recruitment_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @Column(nullable = false)
   private Long candidateId;
    private Long jobId;

    private String mode; // “Online” or “Offline”
    private String status; // “Scheduled”, “Completed”, “Canceled”
    private LocalDateTime interviewDateTime;

    public long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(long candidateId) {
        this.candidateId = candidateId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        if (mode.equalsIgnoreCase("Online") || mode.equalsIgnoreCase("Offline")) {
            this.mode = mode;
        } else {
            throw new IllegalArgumentException("Mode must be 'Online' or 'Offline'");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Completed") || status.equalsIgnoreCase("Canceled")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be 'Scheduled', 'Completed', or 'Canceled'");
        }
    }

    public LocalDateTime getInterviewDateTime() {
        return interviewDateTime;
    }

    public void setInterviewDateTime(LocalDateTime interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
    }

    // toString method to display interview details
    @Override
    public String toString() {
        return "Interview Details:\n" +
               "Candidate ID: " + candidateId + "\n" +
               "Job ID: " + jobId + "\n" +
               "Mode: " + mode + "\n" +
               "Status: " + status + "\n" +
               "Interview Date and Time: " + interviewDateTime + "\n";
    }
}
    

