package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.CategorizationEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engine")
public class CategorizationEngineController {

    @Autowired
    private CategorizationEngineService engineService;

    @PostMapping("/categorize")
    public ResponseEntity<Ticket> categorizeTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(engineService.categorizeTicket(ticket));
    }
}
