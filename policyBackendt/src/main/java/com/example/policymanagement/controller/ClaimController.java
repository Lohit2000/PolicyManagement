package com.example.policymanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.policymanagement.service.*;
import com.example.policymanagement.model.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> createClaim(@Valid @RequestBody Claim claim) {
        Claim savedClaim = claimService.createClaim(claim);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
    }

    @GetMapping("/status/{customerId}")
    public ResponseEntity<List<Claim>> getClaimsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(claimService.getClaimsByCustomerId(customerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long id, @Valid @RequestBody Claim updatedClaim) {
        Claim updated = claimService.updateClaim(id, updatedClaim);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/history/{policyId}")
    public ResponseEntity<List<Claim>> getClaimHistory(@PathVariable Long policyId) {
        return ResponseEntity.ok(claimService.getClaimsByPolicyId(policyId));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}


