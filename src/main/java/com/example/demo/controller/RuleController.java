package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RuleController {
    
    private final CategorizationRuleService ruleService;
    
    @Autowired
    public RuleController(CategorizationRuleService ruleService) {
        this.ruleService = ruleService;
    }
    
    @PostMapping
    public ResponseEntity<CategorizationRule> createRule(@RequestBody CategorizationRule rule) {
        CategorizationRule created = ruleService.createRule(rule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategorizationRule> getRule(@PathVariable Long id) {
        CategorizationRule rule = ruleService.getRule(id);
        return ResponseEntity.ok(rule);
    }
    
    @GetMapping
    public ResponseEntity<List<CategorizationRule>> getAllRules() {
        List<CategorizationRule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CategorizationRule>> searchByKeyword(@RequestParam String keyword) {
        List<CategorizationRule> rules = ruleService.findByKeyword(keyword);
        return ResponseEntity.ok(rules);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategorizationRule> updateRule(@PathVariable Long id, 
                                                        @RequestBody CategorizationRule rule) {
        CategorizationRule updated = ruleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}