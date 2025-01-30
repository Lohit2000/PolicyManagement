package com.example.policymanagement.service;

import com.example.policymanagement.model.Policy;
import com.example.policymanagement.model.Status;
import com.example.policymanagement.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy updatedPolicy) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setName(updatedPolicy.getName());
        policy.setTotalPremiumAmount(updatedPolicy.getTotalPremiumAmount());
        policy.setMaturityAmount(updatedPolicy.getMaturityAmount());
        policy.setNumberOfYears(updatedPolicy.getNumberOfYears());
        policy.setStatus(updatedPolicy.getStatus());
        return policyRepository.save(policy);
    }

    public void closePolicy(Long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        policy.setStatus(Status.valueOf("closed"));
        policyRepository.save(policy);
    }

    public Policy getPolicyDetails(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }
}
