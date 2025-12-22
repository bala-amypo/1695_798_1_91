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

    private String status;          // OPEN, IN_PROGRESS, CLOSED
    private String urgencyLevel;    // LOW, MEDIUM, HIGH

    // ✅ Assigned category for the ticket
    @ManyToOne
    @JoinColumn(name = "assigned_category_id")
    private Category assignedCategory;

    // ✅ Optional user reference
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    // ✅ Backward compatibility methods
    public void setCategory(Category category) {
        this.assignedCategory = category;
    }

    public Category getCategory() {
        return this.assignedCategory;
    }

    public void setAssignedCategory(Category category) {
        this.assignedCategory = category;
    }

    public Category getAssignedCategory() {
        return this.assignedCategory;
    }
}
