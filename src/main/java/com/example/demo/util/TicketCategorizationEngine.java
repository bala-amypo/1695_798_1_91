package com.example.demo.util;

import com.example.demo.model.*;
import java.util.List;

public class TicketCategorizationEngine {

    public void categorize(
        Ticket t,
        List<Category> cats,
        List<CategorizationRule> rules,
        List<UrgencyPolicy> policies,
        List<CategorizationLog> logs
    ) {
        // 1. Apply categorization rules based on priority
        for (CategorizationRule rule : rules) {
            if (t.getDescription().toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                t.setAssignedCategory(rule.getCategory());
                
                // Create log
                CategorizationLog log = new CategorizationLog();
                log.setTicket(t);
                log.setAppliedRule(rule);
                log.setMatchedKeyword(rule.getKeyword());
                log.setAssignedCategory(rule.getCategory().getCategoryName());
                
                // Determine urgency
                for (UrgencyPolicy policy : policies) {
                    if (t.getDescription().toLowerCase().contains(policy.getKeyword().toLowerCase())) {
                        t.setUrgencyLevel(policy.getUrgencyOverride());
                        log.setAssignedUrgency(policy.getUrgencyOverride());
                    }
                }
                if (t.getUrgencyLevel() == null) {
                    t.setUrgencyLevel(t.getAssignedCategory().getDefaultUrgency());
                    log.setAssignedUrgency(t.getAssignedCategory().getDefaultUrgency());
                }

                logs.add(log);
                break; // stop after first matching high-priority rule
            }
        }
    }
}
