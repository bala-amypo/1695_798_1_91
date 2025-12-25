package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService {
    
    private final CategorizationRuleRepository ruleRepository;
    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategorizationRuleServiceImpl(CategorizationRuleRepository ruleRepository, 
                                         CategoryRepository categoryRepository) {
        this.ruleRepository = ruleRepository;
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        if (rule.getCategory() != null && rule.getCategory().getId() != null) {
            categoryRepository.findById(rule.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }
        return ruleRepository.save(rule);
    }
    
    @Override
    public CategorizationRule getRule(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
    }
    
    @Override
    public List<CategorizationRule> getAllRules() {
        return ruleRepository.findAll();
    }
    
    @Override
    public CategorizationRule updateRule(Long id, CategorizationRule rule) {
        CategorizationRule existing = getRule(id);
        existing.setKeyword(rule.getKeyword());
        existing.setMatchType(rule.getMatchType());
        existing.setPriority(rule.getPriority());
        existing.setCategory(rule.getCategory());
        return ruleRepository.save(existing);
    }
    
    @Override
    public void deleteRule(Long id) {
        CategorizationRule rule = getRule(id);
        ruleRepository.delete(rule);
    }
    
    @Override
    public List<CategorizationRule> findByKeyword(String keyword) {
        return ruleRepository.findByKeywordContainingIgnoreCase(keyword);
    }
}