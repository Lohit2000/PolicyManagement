package com.example.policymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.policymanagement.model.*;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByCustomerId(Long customerId);
    List<Claim> findByPolicyId(Long policyId);
}
