package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketCategorizationEngine {

    public void categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs) {

        Category matchedCategory = null;
        CategorizationRule matchedRule = null;

        // 1️⃣ Apply categorization rules
        for (CategorizationRule rule : rules) {
            if (matches(ticket, rule)) {
                matchedCategory = rule.getCategory();
                matchedRule = rule;
                ticket.setAssignedCategory(matchedCategory);
                ticket.setUrgencyLevel(matchedCategory.getDefaultUrgency());
                break;
            }
        }

        // 2️⃣ Apply urgency policy override
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase()
                        .contains(policy.getKeyword().toLowerCase())) {
                ticket.setUrgencyLevel(policy.getUrgencyOverride());
                break;
            }
        }

        // 3️⃣ Default urgency if nothing matched
        if (ticket.getUrgencyLevel() == null) {
            ticket.setUrgencyLevel("LOW");
        }

        // 4️⃣ Log only if rule matched
        if (matchedCategory != null && matchedRule != null) {
            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(matchedRule);
            log.setMatchedKeyword(matchedRule.getKeyword());
            log.setAssignedCategory(matchedCategory.getCategoryName());
            log.setAssignedUrgency(ticket.getUrgencyLevel());
            logs.add(log);
        }
    }

    private boolean matches(Ticket ticket, CategorizationRule rule) {
        String text = (ticket.getTitle() + " " + ticket.getDescription()).toLowerCase();
        return text.contains(rule.getKeyword().toLowerCase());
    }
}
