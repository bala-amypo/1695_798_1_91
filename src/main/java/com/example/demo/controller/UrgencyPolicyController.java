package com.example.demo.controller;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.service.UrgencyPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class UrgencyPolicyController {

    @Autowired
    private UrgencyPolicyService urgencyPolicyService;

    @PostMapping
    public ResponseEntity<UrgencyPolicy> createPolicy(@RequestBody UrgencyPolicy policy) {
        return ResponseEntity.ok(urgencyPolicyService.createPolicy(policy));
    }

    @GetMapping
    public ResponseEntity<List<UrgencyPolicy>> getAllPolicies() {
        return ResponseEntity.ok(urgencyPolicyService.getAllPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(urgencyPolicyService.getPolicyById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> updatePolicy(@PathVariable Long id, @RequestBody UrgencyPolicy policy) {
        return ResponseEntity.ok(urgencyPolicyService.updatePolicy(id, policy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        urgencyPolicyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}
