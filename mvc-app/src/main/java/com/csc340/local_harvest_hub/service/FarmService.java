package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.Farm;
import com.csc340.local_harvest_hub.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }


    public Farm updateFarm(Long id, Farm farmDetails) {
        return farmRepository.findById(id).map(farm -> {
            if (farmDetails.getName() != null) {
                farm.setName(farmDetails.getName());
            }
            if (farmDetails.getLocation() != null) {
                farm.setLocation(farmDetails.getLocation());
            }
            if (farmDetails.getDescription() != null) {
                farm.setDescription(farmDetails.getDescription());
            }
            return farmRepository.save(farm);
        }).orElseThrow(() -> new RuntimeException("Farm not found"));
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
