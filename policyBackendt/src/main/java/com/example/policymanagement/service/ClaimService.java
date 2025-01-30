package com.example.policymanagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.policymanagement.model.*;
import com.example.policymanagement.repository.*;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;

    public Claim createClaim( Claim claim) {
        claim.setStatus("Pending");
        claim.setCreatedAt(LocalDateTime.now());
        claim.setUpdatedAt(LocalDateTime.now());
        System.out.println("in service");
        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsByCustomerId(Long customerId) {
        return claimRepository.findByCustomerId(customerId);
    }

    public List<Claim> getClaimsByPolicyId(Long policyId) {
        return claimRepository.findByPolicyId(policyId);
    }

    public Claim updateClaim(Long id, Claim updatedClaim) {
        return claimRepository.findById(id)
                .map(claim -> {
                    claim.setDescription(updatedClaim.getDescription());
                    claim.setStatus(updatedClaim.getStatus());
                    claim.setUpdatedAt(LocalDateTime.now());
                    return claimRepository.save(claim);
                }).orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}


