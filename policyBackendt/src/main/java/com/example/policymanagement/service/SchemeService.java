package com.example.policymanagement.service;

import com.example.policymanagement.model.Scheme;
import com.example.policymanagement.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepository schemeRepository;

    public Scheme createScheme(Scheme scheme) {
        return schemeRepository.save(scheme); // Save the scheme to the database
    }

    public Optional<Scheme> getSchemeById(Long schemeId) {
        return schemeRepository.findById(schemeId);
    }

    public Scheme updateScheme(Long schemeId, Scheme updatedScheme) {
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        scheme.setName(updatedScheme.getName());
        scheme.setDescription(updatedScheme.getDescription());
        scheme.setEligibilityCriteria(updatedScheme.getEligibilityCriteria());
        scheme.setBenefits(updatedScheme.getBenefits());
        scheme.setTermsAndConditions(updatedScheme.getTermsAndConditions());

        return schemeRepository.save(scheme);
    }

    public void deactivateScheme(Long schemeId) {
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        scheme.setActive(false);
        schemeRepository.save(scheme);
    }

    public List<Scheme> getActiveSchemes() {
        return schemeRepository.findByIsActiveTrue();
    }


}
