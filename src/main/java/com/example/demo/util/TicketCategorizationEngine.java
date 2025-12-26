package com.example.demo.util;

import com.example.demo.model.*;
import java.util.List;

/**
 * Core business logic engine that categorizes tickets
 * based on keyword-matching rules and urgency policies.
 *
 * Verified by tests for:
 *  - Assigning correct category and urgency level
 *  - Applying policy overrides when matched
 *  - Adding CategorizationLog entries
 *  - Default urgency = "LOW" when no match
 */
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

        // 1️⃣ Apply rules — find first matching keyword
        for (CategorizationRule rule : rules) {
            if (matches(ticket, rule)) {
                matchedCategory = rule.getCategory();
                matchedRule = rule;
                assignedUrgency = matchedCategory.getDefaultUrgency();
                break;
            }
        }

        // 2️⃣ Apply urgency policy overrides if matched
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase().contains(policy.getKeyword().toLowerCase())) {
                assignedUrgency = policy.getUrgencyOverride();
                break;
            }
        }

        // 3️⃣ Update the ticket with results
        ticket.setAssignedCategory(matchedCategory);
        ticket.setUrgencyLevel(assignedUrgency);

        // 4️⃣ Log categorization event
        if (matchedCategory != null) {
            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(matchedRule);
            log.setMatchedKeyword(matchedRule != null ? matchedRule.getKeyword() : "none");
            log.setAssignedCategory(matchedCategory.getCategoryName());
            log.setAssignedUrgency(assignedUrgency);
            logs.add(log);
        }
    }

    /**
     * Utility method to check rule match based on matchType.
     */
    private boolean matches(Ticket ticket, CategorizationRule rule) {
        String text = (ticket.getTitle() + " " + ticket.getDescription()).toLowerCase();
        String keyword = rule.getKeyword().toLowerCase();

        return switch (rule.getMatchType().toUpperCase()) {
            case "EXACT" -> text.equals(keyword);
            case "CONTAINS" -> text.contains(keyword);
            case "REGEX" -> text.matches(keyword);
            default -> false;
        };
    }
}
