package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.service.CategorizationEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorization")
public class CategorizationEngineController {

    @Autowired
    private CategorizationEngineService engineService;

    // ✅ Categorize ticket
    @PostMapping("/categorize")
    public Ticket categorizeTicket(@RequestBody Ticket ticket) {
        return engineService.categorizeTicket(ticket);
    }
}
