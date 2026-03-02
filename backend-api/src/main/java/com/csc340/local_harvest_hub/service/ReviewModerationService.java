package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.ReviewModeration;
import com.csc340.local_harvest_hub.repository.ReviewModerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewModerationService {
    
    @Autowired
    private ReviewModerationRepository reviewModerationRepository;
    
    public ReviewModeration createReviewModeration(ReviewModeration moderation) {
        return reviewModerationRepository.save(moderation);
    }
    
    public Optional<ReviewModeration> getReviewModerationById(Long id) {
        return reviewModerationRepository.findById(id);
    }
    
    public List<ReviewModeration> getAllReviewModerations() {
        return reviewModerationRepository.findAll();
    }
    
    public List<ReviewModeration> getModerationsByReviewId(Long reviewId) {
        return reviewModerationRepository.findByReviewId(reviewId);
    }
    
    public List<ReviewModeration> getModerationsByAdminId(Long adminId) {
        return reviewModerationRepository.findByAdminId(adminId);
    }
    
    public ReviewModeration updateReviewModeration(Long id, ReviewModeration moderationDetails) {
        return reviewModerationRepository.findById(id).map(moderation -> {
            moderation.setAction(moderationDetails.getAction());
            moderation.setReason(moderationDetails.getReason());
            return reviewModerationRepository.save(moderation);
        }).orElseThrow(() -> new RuntimeException("ReviewModeration not found"));
    }
    
    public void deleteReviewModeration(Long id) {
        reviewModerationRepository.deleteById(id);
    }
}
