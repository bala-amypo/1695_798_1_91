package com.example.demo.util;

import com.example.demo.model.*;
import java.util.*;

public class TicketCategorizationEngine {

    public CategorizationLog categorizeTicket(Ticket t, List<CategorizationRule> rules, List<UrgencyPolicy> policies) {
        CategorizationLog log = new CategorizationLog();
        log.setTicket(t);

        for (CategorizationRule rule : rules) {
            if (t.getDescription() != null && t.getDescription().toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                t.setAssignedCategory(rule.getCategory());
                log.setAppliedRule(rule);
                log.setNotes("Matched rule: " + rule.getKeyword());
                break;
            }
        }

        if (t.getAssignedCategory() == null && policies != null) {
            for (UrgencyPolicy policy : policies) {
                if (t.getDescription() != null && t.getDescription().toLowerCase().contains(policy.getKeyword().toLowerCase())) {
                    t.setUrgencyLevel(policy.getUrgencyOverride());
                }
            }
        }

        if (t.getAssignedCategory() == null) {
            t.setAssignedCategory("Uncategorized");
            log.setNotes("No rule matched, default applied.");
        }

        return log;
    }
}
