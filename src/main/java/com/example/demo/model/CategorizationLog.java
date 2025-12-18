package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_logs")
public class CategorizationLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private CategorizationRule appliedRule;

    private String matchedKeyword;
    private String assignedCategory;
    private String assignedUrgency;
    private LocalDateTime loggedAt;

    @PrePersist
    protected void onCreate() { loggedAt = LocalDateTime.now(); }
}
