package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public Farmer createFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id).orElse(null);
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        return farmerRepository.findById(id).map(farmer -> {
            if (farmerDetails.getEmail() != null) {
                farmer.setEmail(farmerDetails.getEmail());
            }
            if (farmerDetails.getBio() != null) {
                farmer.setBio(farmerDetails.getBio());
            }
            if (farmerDetails.getStatus() != null) {
                farmer.setStatus(farmerDetails.getStatus());
            }
            return farmerRepository.save(farmer);
        }).orElseThrow(() -> new RuntimeException("Farmer not found"));
    }

    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

    public Farmer getFarmerByEmail(String email) {
        return farmerRepository.findByEmail(email);
    }

    public Farmer authenticate(String email, String password) {
        Farmer farmer = getFarmerByEmail(email);
        if (farmer != null && farmer.getPasswordHash().equals(password)) {
            return farmer;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

}
