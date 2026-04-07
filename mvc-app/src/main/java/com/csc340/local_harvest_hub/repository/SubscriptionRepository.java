package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.Subscription;
import com.csc340.local_harvest_hub.entity.Subscription.SubscriptionStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
  @Query(value = "SELECT s.* FROM subscriptions s WHERE s.customer_id = :customerId", nativeQuery = true)
  List<Subscription> findByCustomerId(Long customerId);

  @Query(value = "SELECT s.* FROM subscriptions s WHERE s.box_id = :boxId", nativeQuery = true)
  List<Subscription> findByProduceBoxId(Long boxId);

  List<Subscription> findByStatus(SubscriptionStatus status);

  @Query(value = "SELECT s.* FROM subscriptions s JOIN produce_boxes pb ON s.box_id = pb.box_id JOIN farms f ON pb.farm_id = f.farm_id WHERE f.farm_id = :farmId", nativeQuery = true)
  List<Subscription> findByFarmId(Long farmId);
}
