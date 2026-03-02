package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.ReviewModeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewModerationRepository extends JpaRepository<ReviewModeration, Long> {
  @Query(value = "SELECT rm FROM ReviewModeration rm WHERE rm.review_id = :reviewId", nativeQuery = true)
  List<ReviewModeration> findByReviewId(Long reviewId);

  @Query(value = "SELECT rm FROM ReviewModeration rm WHERE rm.admin_id = :adminId", nativeQuery = true)
  List<ReviewModeration> findByAdminId(Long adminId);
}
