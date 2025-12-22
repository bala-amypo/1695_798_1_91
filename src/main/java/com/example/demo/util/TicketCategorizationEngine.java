package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;

@Component
public class TicketCategorizationEngine {

    public void categorize(Ticket t, CategorizationRule rule, UrgencyPolicy policy) {
        if (t == null) return;

        // ✅ Assign category based on rule
        if (rule != null && rule.getCategory() != null) {
            t.setAssignedCategory(rule.getCategory());
        }

        // ✅ Assign urgency level if policy exists
        if (policy != null && policy.getUrgencyOverride() != null) {
            t.setUrgencyLevel(policy.getUrgencyOverride());
        }
    }

    // Optional scoring or keyword match logic
    public boolean matchesRule(Ticket t, CategorizationRule rule) {
        if (t == null || rule == null || rule.getKeyword() == null) return false;
        String desc = (t.getDescription() != null) ? t.getDescription().toLowerCase() : "";
        return desc.contains(rule.getKeyword().toLowerCase());
    }
}
