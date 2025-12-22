package com.example.demo.controller;

import com.example.demo.model.CategorizationLog;
import com.example.demo.service.CategorizationEngineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorize")
@Tag(name = "Categorization Engine")
public class CategorizationEngineController {

    private final CategorizationEngineService engineService;

    public CategorizationEngineController(CategorizationEngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/run/{ticketId}")
    public void categorizeTicket(@PathVariable Long ticketId) {
        engineService.categorizeTicket(ticketId);
    }

    @GetMapping("/logs/{ticketId}")
    public List<CategorizationLog> getLogsForTicket(@PathVariable Long ticketId) {
        return engineService.getLogsForTicket(ticketId);
    }

    @GetMapping("/log/{id}")
    public CategorizationLog getLog(@PathVariable Long id) {
        return engineService.getLog(id);
    }
}
