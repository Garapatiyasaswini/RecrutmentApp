package com.recruitment.recruitment_app.model;

import java.util.List;

import jakarta.persistence.*;
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String salary;
    @ElementCollection
    private List<String> requiredSkills;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getlocation() { return location; }
    public void setlocation(String location) { this.location = location; }

    public List<String> getskillsrequired() { return requiredSkills; }
    public void setskillsrequired(List<String> requiredSkills) { this.requiredSkills = requiredSkills;}

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this. salary = salary;
    }
}


