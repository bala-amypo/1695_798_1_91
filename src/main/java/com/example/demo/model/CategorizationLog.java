package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_logs")
public class CategorizationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
    
    @ManyToOne
    @JoinColumn(name = "rule_id")
    private CategorizationRule appliedRule;
    
    private String categoryName;
    private String urgencyLevel;
    
    @Column(name = "categorized_at")
    private LocalDateTime categorizedAt;
    
    @PrePersist
    protected void prePersist() {
        if (categorizedAt == null) {
            categorizedAt = LocalDateTime.now();
        }
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public CategorizationRule getAppliedRule() { return appliedRule; }
    public void setAppliedRule(CategorizationRule appliedRule) { this.appliedRule = appliedRule; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    public LocalDateTime getCategorizedAt() { return categorizedAt; }
    public void setCategorizedAt(LocalDateTime categorizedAt) { this.categorizedAt = categorizedAt; }
}