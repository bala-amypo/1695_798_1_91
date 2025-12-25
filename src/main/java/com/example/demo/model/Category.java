package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories", 
       uniqueConstraints = @UniqueConstraint(name = "uk_category_name", columnNames = "category_name"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "category_name", nullable = false, unique = true, length = 100)
    private String categoryName;
    
    @Column(name = "default_urgency", length = 20)
    private String defaultUrgency = "MEDIUM";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToMany
    @JoinTable(
        name = "category_urgency_policies",
        joinColumns = @JoinColumn(name = "category_id", 
                                 foreignKey = @ForeignKey(name = "fk_category_policy_category")),
        inverseJoinColumns = @JoinColumn(name = "policy_id",
                                       foreignKey = @ForeignKey(name = "fk_category_policy_policy")),
        uniqueConstraints = @UniqueConstraint(name = "uk_category_policy", 
                                            columnNames = {"category_id", "policy_id"})
    )
    private Set<UrgencyPolicy> urgencyPolicies = new HashSet<>();
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters remain the same
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Set<UrgencyPolicy> getUrgencyPolicies() { return urgencyPolicies; }
    public void setUrgencyPolicies(Set<UrgencyPolicy> urgencyPolicies) { this.urgencyPolicies = urgencyPolicies; }
}