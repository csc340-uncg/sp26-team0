package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.Review;
import com.csc340.local_harvest_hub.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
    
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    public List<Review> getReviewsBySubscriptionId(Long subscriptionId) {
        return reviewRepository.findBySubscriptionId(subscriptionId);
    }
    
    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id).map(review -> {
            review.setFreshnessRating(reviewDetails.getFreshnessRating());
            review.setDeliveryRating(reviewDetails.getDeliveryRating());
            review.setValueRating(reviewDetails.getValueRating());
            review.setComment(reviewDetails.getComment());
            review.setReplyText(reviewDetails.getReplyText());
            return reviewRepository.save(review);
        }).orElseThrow(() -> new RuntimeException("Review not found"));
    }
    
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
