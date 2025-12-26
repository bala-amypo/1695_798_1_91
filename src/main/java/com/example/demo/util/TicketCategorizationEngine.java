package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component   // 👈 THIS IS THE FIX
public class TicketCategorizationEngine {

    public void categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs) {

        Category matchedCategory = null;
        String assignedUrgency = "LOW";
        CategorizationRule matchedRule = null;

        for (CategorizationRule rule : rules) {
            if (matches(ticket, rule)) {
                matchedCategory = rule.getCategory();
                matchedRule = rule;
                assignedUrgency = matchedCategory.getDefaultUrgency();
                break;
            }
        }

        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase()
                        .contains(policy.getKeyword().toLowerCase())) {
                assignedUrgency = policy.getUrgencyOverride();
                break;
            }
        }

        ticket.setAssignedCategory(matchedCategory);
        ticket.setUrgencyLevel(assignedUrgency);

        if (matchedCategory != null) {
            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(matchedRule);
            log.setMatchedKeyword(matchedRule.getKeyword());
            log.setAssignedCategory(matchedCategory.getCategoryName());
            log.setAssignedUrgency(assignedUrgency);
            logs.add(log);
        }
    }

    private boolean matches(Ticket ticket, CategorizationRule rule) {
        String text = (ticket.getTitle() + " " + ticket.getDescription()).toLowerCase();
        String keyword = rule.getKeyword().toLowerCase();
        return text.contains(keyword);
    }
}
