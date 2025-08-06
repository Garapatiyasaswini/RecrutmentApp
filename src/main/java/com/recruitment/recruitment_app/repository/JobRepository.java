package com.recruitment.recruitment_app.repository;

import com.recruitment.recruitment_app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
