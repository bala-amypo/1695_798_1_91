package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategorizationEngineService {

    @Autowired
    private TicketCategorizationEngine engine;

    public void applyCategorization(Ticket ticket, CategorizationRule rule, UrgencyPolicy policy) {
        engine.categorize(ticket, rule, policy);

        // Ensure consistency
        if (rule != null && rule.getCategory() != null) {
            ticket.setCategory(rule.getCategory());
        }
    }
}
