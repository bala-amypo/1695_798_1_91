package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 1000, nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category assignedCategory;
    
    private String urgencyLevel;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (urgencyLevel == null) {
            urgencyLevel = "LOW";
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        if (description != null && description.length() < 10) {
            throw new IllegalArgumentException("Description must be at least 10 characters");
        }
        this.description = description; 
    }
    public Category getAssignedCategory() { return assignedCategory; }
    public void setAssignedCategory(Category assignedCategory) { this.assignedCategory = assignedCategory; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}