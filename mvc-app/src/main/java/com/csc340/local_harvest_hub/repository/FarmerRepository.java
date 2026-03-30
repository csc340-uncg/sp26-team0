package com.csc340.local_harvest_hub.repository;

import com.csc340.local_harvest_hub.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Farmer findByEmail(String email);
}
