package com.example.demo.controller;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.impl.CategorizationRuleServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Rules", description = "Categorization rule management endpoints")
public class CategorizationRuleController {

    private final CategorizationRuleServiceImpl ruleService;

    public CategorizationRuleController(CategorizationRuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<CategorizationRule> createRule(
            @PathVariable Long categoryId,
            @RequestBody CategorizationRule rule) {
        return ResponseEntity.ok(ruleService.createRule(categoryId, rule));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CategorizationRule>> getRulesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(ruleService.getRulesByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorizationRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }
}
