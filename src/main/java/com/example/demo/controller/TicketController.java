package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    
    private final TicketService ticketService;
    
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket created = ticketService.createTicket(ticket);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        return ResponseEntity.ok(ticket);
    }
    
    @GetMapping