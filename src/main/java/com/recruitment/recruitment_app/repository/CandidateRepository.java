package com.recruitment.recruitment_app.repository;

import com.recruitment.recruitment_app.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Optional: Custom query methods
    //Optional<Candidate> findByEmail(String email);
    //Optional<Candidate> findByPhoneNumber(String phoneNumber);

    // You can add more if needed (e.g., find by job)
}
