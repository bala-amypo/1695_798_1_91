package com.example.demo.exception;

/**
 * Used for invalid or duplicate resource creation attempts.
 * 
 * Tests expect messages containing:
 * "Email already in use" or other business validation errors.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
