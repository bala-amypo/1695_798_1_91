package com.example.demo.exception;

/**
 * Thrown when an entity such as User, Ticket, Category, Rule, Policy, or Log
 * cannot be found in the database.
 * 
 * Required by tests to include keywords:
 * "User not found", "Ticket not found", "Category not found",
 * "Rule not found", "Policy not found", "Log not found"
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
