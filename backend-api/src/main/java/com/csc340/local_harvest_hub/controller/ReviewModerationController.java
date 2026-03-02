package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.ReviewModeration;
import com.csc340.local_harvest_hub.service.ReviewModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review-moderations")
public class ReviewModerationController {

    @Autowired
    private ReviewModerationService reviewModerationService;

    @PostMapping
    public ResponseEntity<ReviewModeration> createReviewModeration(@RequestBody ReviewModeration moderation) {
        ReviewModeration createdModeration = reviewModerationService.createReviewModeration(moderation);
        return new ResponseEntity<>(createdModeration, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewModeration>> getAllReviewModerations() {
        List<ReviewModeration> moderations = reviewModerationService.getAllReviewModerations();
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewModeration> getReviewModerationById(@PathVariable Long id) {
        Optional<ReviewModeration> moderation = reviewModerationService.getReviewModerationById(id);
        return moderation.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<ReviewModeration>> getModerationsByReviewId(@PathVariable Long reviewId) {
        List<ReviewModeration> moderations = reviewModerationService.getModerationsByReviewId(reviewId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<ReviewModeration>> getModerationsByAdminId(@PathVariable Long adminId) {
        List<ReviewModeration> moderations = reviewModerationService.getModerationsByAdminId(adminId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewModeration> updateReviewModeration(@PathVariable Long id, @RequestBody ReviewModeration moderationDetails) {
        try {
            ReviewModeration updatedModeration = reviewModerationService.updateReviewModeration(id, moderationDetails);
            return new ResponseEntity<>(updatedModeration, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReviewModeration(@PathVariable Long id) {
        reviewModerationService.deleteReviewModeration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
