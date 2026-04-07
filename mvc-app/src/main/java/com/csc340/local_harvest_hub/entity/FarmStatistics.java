package com.csc340.local_harvest_hub.entity;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class FarmStatistics {

  // Subscriber Statistics
  private long totalSubscribers;
  private long activeSubscribers;

  // Box Statistics
  private int totalBoxes;
  private int activeBoxes;
  private Map<String, Long> mostPopularBoxes; // Box name -> subscriber count

  // Rating Statistics
  private double averageOverallRating;
  private double averageFreshnessRating;
  private double averageDeliveryRating;
  private Map<String, Double> ratingsByBox; // Box name -> rating

  // Review Statistics
  private long totalReviews;
  private double responseRate; // Percentage of reviews with farmer responses
}
