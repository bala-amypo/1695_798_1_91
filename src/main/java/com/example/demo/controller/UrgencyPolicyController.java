package com.example.demo.controller;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.service.UrgencyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class UrgencyPolicyController {
    
    private final UrgencyPolicyService policyService;
    
    @Autowired
    public UrgencyPolicyController(UrgencyPolicyService policyService) {
        this.policyService = policyService;
    }
    
    @PostMapping
    public ResponseEntity<UrgencyPolicy> createPolicy(@RequestBody UrgencyPolicy policy) {
        UrgencyPolicy created = policyService.createPolicy(policy);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> getPolicy(@PathVariable Long id) {
        UrgencyPolicy policy = policyService.getPolicy(id);
        return ResponseEntity.ok(policy);
    }
    
    @GetMapping
    public ResponseEntity<List<UrgencyPolicy>> getAllPolicies() {
        List<UrgencyPolicy> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<UrgencyPolicy>> searchByKeyword(@RequestParam String keyword) {
        List<UrgencyPolicy> policies = policyService.findByKeyword(keyword);
        return ResponseEntity.ok(policies);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> updatePolicy(@PathVariable Long id, 
                                                      @RequestBody UrgencyPolicy policy) {
        UrgencyPolicy updated = policyService.updatePolicy(id, policy);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}