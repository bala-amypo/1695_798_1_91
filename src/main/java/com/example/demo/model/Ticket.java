package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String status; // e.g., OPEN, IN_PROGRESS, CLOSED

    // ✅ For urgency or severity tracking
    private String urgencyLevel; // e.g., LOW, MEDIUM, HIGH

    // ✅ The category assigned (used in multiple services)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // ✅ Optional: user who created the ticket
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
