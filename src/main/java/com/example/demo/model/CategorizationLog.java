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

    // The ticket that was categorized
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    // The rule that was applied for categorization
    @ManyToOne
    @JoinColumn(name = "applied_rule_id")
    private CategorizationRule appliedRule;

    // Optional field if urgency policy was also applied
    @ManyToOne
    @JoinColumn(name = "urgency_policy_id", nullable = true)
    private UrgencyPolicy appliedPolicy;

    // Description or notes about the categorization
    private String message;

    // Timestamp of when categorization occurred
    private LocalDateTime categorizedAt = LocalDateTime.now();
}
