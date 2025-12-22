package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "urgency_policies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrgencyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level; // HIGH, MEDIUM, LOW

    private String description;

    // ✅ Fields expected by engine
    private String keyword; // keyword to match (e.g. "critical", "down", "urgent")
    private String urgencyOverride; // e.g. escalate to HIGH
}
