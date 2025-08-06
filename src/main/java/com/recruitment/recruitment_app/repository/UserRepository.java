package com.recruitment.recruitment_app.repository;
import com.recruitment.recruitment_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email); // common lookup method
    // Optional<User> findByUsername(String username); 
    boolean existsByEmail(String email); // useful for registration
}