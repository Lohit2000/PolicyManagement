package com.example.policymanagement.service;


import org.springframework.stereotype.Service;
import com.example.policymanagement.model.Feedback;
import com.example.policymanagement.repository.FeedbackRepository;


import java.util.List;
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    // Submit Feedback
    public Feedback submitFeedback(Feedback feedback) {
        feedback.setStatus("Pending");
        feedback.setAcknowledgmentMessage("Thank you for your feedback, " + feedback.getCustomer_name() + "!");
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        existingFeedback.setComments(updatedFeedback.getComments());
        existingFeedback.setCustomer_name(updatedFeedback.getCustomer_name());
        existingFeedback.setRating(updatedFeedback.getRating());

        return feedbackRepository.save(existingFeedback);
    }

    public List<Feedback> getFeedbackByStatus(String status) {
        return feedbackRepository.findByStatus(status);
    }

    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));

        feedback.setStatus(status);
        return feedbackRepository.save(feedback);
    }
}
