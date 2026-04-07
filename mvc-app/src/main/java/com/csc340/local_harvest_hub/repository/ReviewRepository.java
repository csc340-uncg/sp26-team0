package com.csc340.local_harvest_hub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csc340.local_harvest_hub.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Query(value = "SELECT r.* FROM reviews r, subscriptions s, produce_boxes pb, farms f WHERE r.subscription_id = s.subscription_id AND s.box_id = pb.box_id AND pb.farm_id = f.farm_id AND f.farm_id = :farmId", nativeQuery = true)
  List<Review> findByFarmId(Long farmId);

  @Query(value = "SELECT r.* FROM reviews r WHERE r.customer_id = :customerId", nativeQuery = true)
  List<Review> findByCustomerId(Long customerId);

  @Query(value = "SELECT r.* FROM reviews r WHERE r.subscription_id = :subscriptionId", nativeQuery = true)
  List<Review> findBySubscriptionId(Long subscriptionId);

}
