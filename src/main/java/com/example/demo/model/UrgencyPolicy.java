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

    private String keyword;
    private String urgencyOverride; // e.g., HIGH, MEDIUM, LOW
    private String level;
    private String description;
}
