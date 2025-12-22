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

    private String status;

    // ✅ Used by categorization engine
    @ManyToOne
    @JoinColumn(name = "assigned_category_id")
    private Category assignedCategory;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
