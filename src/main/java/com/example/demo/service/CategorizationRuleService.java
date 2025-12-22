package com.example.demo.service;

import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationRuleService {

    @Autowired
    private CategorizationRuleRepository ruleRepository;

    public CategorizationRule createRule(CategorizationRule rule) {
        return ruleRepository.save(rule);
    }

    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }

    public CategorizationRule getRuleById(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }

    public CategorizationRule updateRule(Long id, CategorizationRule updatedRule) {
        CategorizationRule existing = getRuleById(id);
        if (existing == null) return null;
        existing.setKeyword(updatedRule.getKeyword());
        existing.setCategory(updatedRule.getCategory());
        return ruleRepository.save(existing);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }
}
