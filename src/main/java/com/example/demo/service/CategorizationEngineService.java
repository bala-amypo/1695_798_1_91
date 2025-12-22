package com.example.demo.service;

import com.example.demo.model.Ticket;
import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationEngineService {

    @Autowired
    private CategorizationRuleRepository ruleRepository;

    public Ticket categorizeTicket(Ticket ticket) {
        List<CategorizationRule> rules = ruleRepository.findAll();
        for (CategorizationRule rule : rules) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase().contains(rule.getKeyword().toLowerCase())) {
                ticket.setCategory(rule.getCategory());
                break;
            }
        }
        return ticket;
    }
}
