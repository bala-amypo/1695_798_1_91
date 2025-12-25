package com.example.demo.service;

import com.example.demo.model.CategorizationRule;
import java.util.List;

public interface CategorizationRuleService {
    CategorizationRule createRule(CategorizationRule rule);
    CategorizationRule getRule(Long id);
    List<CategorizationRule> getAllRules();
    CategorizationRule updateRule(Long id, CategorizationRule rule);
    void deleteRule(Long id);
    List<CategorizationRule> findByKeyword(String keyword);
}