package com.example.policymanagement.controller;

import com.example.policymanagement.model.Feedback;
import com.example.policymanagement.service.FeedbackService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Endpoint to submit feedback
    @PostMapping("/submit")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

    // Endpoint to update feedback
    @PutMapping("/update/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback updatedFeedback = feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.ok(updatedFeedback);
    }

    @GetMapping("/review")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Feedback>> getFeedbackByStatus(@RequestParam String status) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByStatus(status);
        return ResponseEntity.ok(feedbackList);
    }

    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Feedback> updateFeedbackStatus(@PathVariable Long id, @RequestParam String status) {
        Feedback feedback = feedbackService.updateFeedbackStatus(id, status);
        return ResponseEntity.ok(feedback);
    }
}


