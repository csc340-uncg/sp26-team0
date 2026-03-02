package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.ListingModeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingModerationRepository extends JpaRepository<ListingModeration, Long> {
  @Query(value = "SELECT lm FROM ListingModeration lm WHERE lm.produce_box_id = :boxId", nativeQuery = true)
  List<ListingModeration> findByProduceBoxId(Long boxId);

  @Query(value = "SELECT lm FROM ListingModeration lm WHERE lm.admin_id = :adminId", nativeQuery = true)
  List<ListingModeration> findByAdminId(Long adminId);
}
