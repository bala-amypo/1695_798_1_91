package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.util.TicketCategorizationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorizationEngineService {

    @Autowired
    private TicketCategorizationEngine engine;

    // ✅ Categorize a ticket using rule and policy
    public Ticket categorizeTicket(Ticket ticket) {
        // Placeholder rule/policy (you can wire repositories later)
        CategorizationRule dummyRule = new CategorizationRule();
        dummyRule.setKeyword("error");

        UrgencyPolicy dummyPolicy = new UrgencyPolicy();
        dummyPolicy.setUrgencyOverride("HIGH");

        engine.categorize(ticket, dummyRule, dummyPolicy);
        return ticket;
    }
}
