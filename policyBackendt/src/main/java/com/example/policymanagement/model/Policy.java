package com.example.policymanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private Double totalPremiumAmount;
    private Double maturityAmount;
    private Integer numberOfYears;
    private Status status; // active, inactive, closed
    private AnnuityTerm annuityTerm; // quarterly, half-yearly, annual, one-time

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Double getTotalPremiumAmount() {
        return totalPremiumAmount;
    }

    public void setTotalPremiumAmount(Double totalPremiumAmount) {
        this.totalPremiumAmount = totalPremiumAmount;
    }

    public Double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(Double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AnnuityTerm getAnnuityTerm() {
        return annuityTerm;
    }

    public void setAnnuityTerm(AnnuityTerm annuityTerm) {
        this.annuityTerm = annuityTerm;
    }

    /*
        @ManyToOne
        private User user;

        @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
        private List<Payment> payments = new ArrayList<>();

        @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
        private List<Claim> claims = new ArrayList<>();
        // Getters and setters
        */

}



