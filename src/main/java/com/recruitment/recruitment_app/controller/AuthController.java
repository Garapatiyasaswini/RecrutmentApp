package com.recruitment.recruitment_app.controller;

import com.recruitment.recruitment_app.model.User;
import com.recruitment.recruitment_app.repository.UserRepository;
import com.recruitment.recruitment_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        User user = userRepository.findByEmail(credentials.get("username")).orElseThrow();

        if (passwordEncoder.matches(credentials.get("password"), user.getPassword())) {
            String message;
            switch (user.getRole()) {
                case ADMIN -> message = "Welcome Admin!";
                case RECRUITER -> message = "Recruiter can view and manage jobs.";
                case CANDIDATE -> message = "View and apply for jobs.";
                default -> message = "Welcome!";
            }
            String token = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(Map.of("token", token,  "role", user.getRole().toString(),
            "message", message));
        } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}