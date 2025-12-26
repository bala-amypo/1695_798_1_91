package com.example.demo.controller;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.service.impl.UrgencyPolicyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@Tag(name = "Urgency Policies", description = "Urgency policy management endpoints")
public class UrgencyPolicyController {

    private final UrgencyPolicyServiceImpl policyService;

    public UrgencyPolicyController(UrgencyPolicyServiceImpl policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public ResponseEntity<UrgencyPolicy> createPolicy(@RequestBody UrgencyPolicy policy) {
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    @GetMapping
    public ResponseEntity<List<UrgencyPolicy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> getPolicy(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicy(id));
    }
}
