package com.example.policymanagement.controller;

import com.example.policymanagement.model.Scheme;
import com.example.policymanagement.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scheme")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    // Administrator: Create a new scheme
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")  // Ensure that only admins can create schemes
    public ResponseEntity<Scheme> createScheme(@RequestBody Scheme newScheme) {
        Scheme createdScheme = schemeService.createScheme(newScheme);
        return ResponseEntity.ok(createdScheme);
    }

    // Administrator: Update an existing scheme
    @PutMapping("/update/{schemeId}")
    public ResponseEntity<?> updateScheme(
            @PathVariable Long schemeId,
            @RequestBody Scheme updatedScheme
    ) {
        Optional<Scheme> existingScheme = schemeService.getSchemeById(schemeId);
        if (existingScheme.isEmpty()) {
            return ResponseEntity.status(404).body("Scheme not found");
        }

        Scheme updated = schemeService.updateScheme(schemeId, updatedScheme);
        return ResponseEntity.ok(updated);
    }

    // Administrator: Deactivate a scheme
    @PutMapping("/deactivate/{schemeId}")
    public ResponseEntity<?> deactivateScheme(@PathVariable Long schemeId) {
        Optional<Scheme> existingScheme = schemeService.getSchemeById(schemeId);
        if (existingScheme.isEmpty()) {
            return ResponseEntity.status(404).body("Scheme not found");
        }

        schemeService.deactivateScheme(schemeId);
        return ResponseEntity.ok("Scheme deactivated successfully");
    }

    // Customers and admin: View all active schemes
    @GetMapping("/view")
    public ResponseEntity<List<Scheme>> viewAllActiveSchemes() {
        List<Scheme> activeSchemes = schemeService.getActiveSchemes();
        return ResponseEntity.ok(activeSchemes);
    }

    // Administrator: View customers enrolled in a specific scheme


}
