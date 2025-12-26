package com.example.demo.controller;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;
import com.example.demo.service.impl.CategorizationEngineServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categorize")
@Tag(name = "Categorization Engine", description = "Endpoints to run categorization and view logs")
public class CategorizationEngineController {

    private final CategorizationEngineServiceImpl engineService;

    public CategorizationEngineController(CategorizationEngineServiceImpl engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/run/{ticketId}")
    public ResponseEntity<Ticket> runCategorization(@PathVariable Long ticketId) {
        return ResponseEntity.ok(engineService.categorizeTicket(ticketId));
    }

    @GetMapping("/logs/{ticketId}")
    public ResponseEntity<List<CategorizationLog>> getLogs(@PathVariable Long ticketId) {
        return ResponseEntity.ok(engineService.getLogsForTicket(ticketId));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<CategorizationLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(engineService.getLog(id));
    }
}
