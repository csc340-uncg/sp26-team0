package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.Farmer;
import com.csc340.local_harvest_hub.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        Farmer createdFarmer = farmerService.createFarmer(farmer);
        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerService.getAllFarmers();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Farmer> getFarmerByEmail(@PathVariable String email) {
        Farmer farmer = farmerService.getFarmerByEmail(email);
        return farmer != null ? new ResponseEntity<>(farmer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer farmerDetails) {
        try {
            Farmer updatedFarmer = farmerService.updateFarmer(id, farmerDetails);
            return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
