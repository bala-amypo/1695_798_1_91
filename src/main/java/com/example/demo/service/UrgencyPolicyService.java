package com.example.demo.service;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.repository.UrgencyPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrgencyPolicyService {

    @Autowired
    private UrgencyPolicyRepository urgencyPolicyRepository;

    public UrgencyPolicy createPolicy(UrgencyPolicy policy) {
        return urgencyPolicyRepository.save(policy);
    }

    public List<UrgencyPolicy> getAllPolicies() {
        return urgencyPolicyRepository.findAll();
    }

    public UrgencyPolicy getPolicyById(Long id) {
        return urgencyPolicyRepository.findById(id).orElse(null);
    }

    public UrgencyPolicy updatePolicy(Long id, UrgencyPolicy updated) {
        UrgencyPolicy existing = getPolicyById(id);
        if (existing == null) return null;
        existing.setLevel(updated.getLevel());
        existing.setDescription(updated.getDescription());
        return urgencyPolicyRepository.save(existing);
    }

    public void deletePolicy(Long id) {
        urgencyPolicyRepository.deleteById(id);
    }
}
