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

    public List<Review> getReviewsByFarmId(Long farmId) {
        return reviewRepository.findByFarmId(farmId);
    }

    public double getAverageRatingForFarm(Long farmId) {
        List<Review> reviews = getReviewsByFarmId(farmId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getFreshnessRating();
            totalRating += review.getDeliveryRating();
            totalRating += review.getValueRating();
        }
        return totalRating / (reviews.size() * 3); // Average of all three ratings
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
