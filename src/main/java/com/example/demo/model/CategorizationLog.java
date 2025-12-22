package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorization_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorizationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "applied_rule_id")
    private CategorizationRule appliedRule;

    @ManyToOne
    @JoinColumn(name = "urgency_policy_id", nullable = true)
    private UrgencyPolicy appliedPolicy;

    private String message;

    // ✅ New field for the engine to write notes
    private String notes;

    private LocalDateTime categorizedAt = LocalDateTime.now();
}
