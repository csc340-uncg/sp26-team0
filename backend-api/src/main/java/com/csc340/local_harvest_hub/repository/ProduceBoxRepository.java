package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.ProduceBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceBoxRepository extends JpaRepository<ProduceBox, Long>, JpaSpecificationExecutor<ProduceBox> {
  @Query(value = "SELECT pb.* FROM produce_boxes pb WHERE pb.farm_id = :farmId", nativeQuery = true)
  List<ProduceBox> findByFarmId(Long farmId);

  List<ProduceBox> findByStatus(String status);

}
