package com.example.demo.util;

import com.example.demo.model.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TicketCategorizationEngine {
    
    public void categorize(Ticket ticket, 
                          List<Category> categories,
                          List<CategorizationRule> rules,
                          List<UrgencyPolicy> policies,
                          List<CategorizationLog> logs) {
        
        // Reset to defaults
        ticket.setAssignedCategory(null);
        ticket.setUrgencyLevel("LOW");
        
        // Find matching rule with highest priority
        CategorizationRule matchedRule = null;
        int highestPriority = -1;
        
        for (CategorizationRule rule : rules) {
            if (matchesRule(ticket, rule) && rule.getPriority() > highestPriority) {
                highestPriority = rule.getPriority();
                matchedRule = rule;
            }
        }
        
        // Apply category if rule matched
        if (matchedRule != null) {
            ticket.setAssignedCategory(matchedRule.getCategory());
            ticket.setUrgencyLevel(matchedRule.getCategory().getDefaultUrgency());
        }
        
        // Apply urgency policy overrides
        for (UrgencyPolicy policy : policies) {
            if (matchesPolicy(ticket, policy)) {
                ticket.setUrgencyLevel(policy.getUrgencyOverride());
                break;
            }
        }
        
        // Create log entry
        CategorizationLog log = new CategorizationLog();
        log.setTicket(ticket);
        log.setAppliedRule(matchedRule);
        log.setCategoryName(ticket.getAssignedCategory() != null ? 
                          ticket.getAssignedCategory().getCategoryName() : "UNCATEGORIZED");
        log.setUrgencyLevel(ticket.getUrgencyLevel());
        logs.add(log);
    }
    
    private boolean matchesRule(Ticket ticket, CategorizationRule rule) {
        String keyword = rule.getKeyword().toLowerCase();
        String description = ticket.getDescription().toLowerCase();
        String title = ticket.getTitle().toLowerCase();
        
        switch (rule.getMatchType()) {
            case "CONTAINS":
                return description.contains(keyword) || title.contains(keyword);
            case "STARTS_WITH":
                return description.startsWith(keyword) || title.startsWith(keyword);
            case "ENDS_WITH":
                return description.endsWith(keyword) || title.endsWith(keyword);
            default:
                return description.contains(keyword) || title.contains(keyword);
        }
    }
    
    private boolean matchesPolicy(Ticket ticket, UrgencyPolicy policy) {
        String keyword = policy.getKeyword().toLowerCase();
        String description = ticket.getDescription().toLowerCase();
        String title = ticket.getTitle().toLowerCase();
        
        return description.contains(keyword) || title.contains(keyword);
    }
}