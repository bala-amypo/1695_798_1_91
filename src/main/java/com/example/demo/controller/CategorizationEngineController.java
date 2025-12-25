package com.example.demo.controller;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;
import com.example.demo.service.CategorizationEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorize")
public class CategorizationEngineController {
    
    private final CategorizationEngineService engineService;
    
    @Autowired
    public CategorizationEngineController(CategorizationEngineService engineService) {
        this.engineService = engineService;
    }
    
    @PostMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> categorizeTicket(@PathVariable Long ticketId) {
        Ticket categorized = engineService.categorizeTicket(ticketId);
        return ResponseEntity.ok(categorized);
    }
    
    @GetMapping("/logs/ticket/{ticketId}")
    public ResponseEntity<List<CategorizationLog>> getLogsForTicket(@PathVariable Long ticketId) {
        List<CategorizationLog> logs = engineService.getLogsForTicket(ticketId);
        return ResponseEntity.ok(logs);
    }
    
    @GetMapping("/logs/{logId}")
    public ResponseEntity<CategorizationLog> getLog(@PathVariable Long logId) {
        CategorizationLog log = engineService.getLog(logId);
        return ResponseEntity.ok(log);
    }
}