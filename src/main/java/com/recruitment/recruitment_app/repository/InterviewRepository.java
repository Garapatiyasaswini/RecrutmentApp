package com.recruitment.recruitment_app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.recruitment.recruitment_app.model.Interview;

import java.util.List;
//import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByCandidateId(long candidateId);
}
    