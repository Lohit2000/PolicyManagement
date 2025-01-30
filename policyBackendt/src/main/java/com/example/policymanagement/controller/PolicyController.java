package com.example.policymanagement.controller;

import com.example.policymanagement.model.Policy;
import com.example.policymanagement.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.updatePolicy(id, policy));
    }

    @PutMapping("/close/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> closePolicy(@PathVariable Long id) {
        policyService.closePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getPolicy/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Policy> getPolicyDetails(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyDetails(id));
    }
}
