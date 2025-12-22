package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorization_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorizationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Keyword or phrase to detect in ticket description
    @Column(nullable = false)
    private String keyword;

    // Optional — can store pattern logic or rule explanation
    private String pattern;

    // Optional weight or priority of rule matching
    private int priority = 1;

    // ✅ Target category for this rule
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Optional message to describe rule purpose
    private String description;
}
