package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;
    
    @Column(name = "default_urgency")
    private String defaultUrgency = "MEDIUM";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToMany
    @JoinTable(
        name = "category_urgency_policies",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "policy_id")
    )
    private Set<UrgencyPolicy> urgencyPolicies = new HashSet<>();
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Set<UrgencyPolicy> getUrgencyPolicies() { return urgencyPolicies; }
    public void setUrgencyPolicies(Set<UrgencyPolicy> urgencyPolicies) { this.urgencyPolicies = urgencyPolicies; }
}