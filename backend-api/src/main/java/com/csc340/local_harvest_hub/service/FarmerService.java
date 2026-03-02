package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public Farmer createFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        return farmerRepository.findById(id).map(farmer -> {
            farmer.setEmail(farmerDetails.getEmail());
            farmer.setBio(farmerDetails.getBio());
            farmer.setStatus(farmerDetails.getStatus());
            return farmerRepository.save(farmer);
        }).orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

    public Farmer getFarmerByEmail(String email) {
        return farmerRepository.findByEmail(email);
    }
}
