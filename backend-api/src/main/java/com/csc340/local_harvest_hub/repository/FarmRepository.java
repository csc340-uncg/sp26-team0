package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
  @Query(value = "SELECT f FROM Farm f WHERE f.farmer_id = :farmerId", nativeQuery = true)
  List<Farm> findByFarmerId(Long farmerId);
}
