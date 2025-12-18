package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String createdBy;
    private LocalDateTime createdAt;

    @ManyToOne
    private Category assignedCategory;

    private String urgencyLevel; // LOW / MEDIUM / HIGH / CRITICAL

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }
}
