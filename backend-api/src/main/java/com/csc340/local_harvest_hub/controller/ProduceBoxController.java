package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.ProduceBox;
import com.csc340.local_harvest_hub.service.ProduceBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produce-boxes")
public class ProduceBoxController {

    @Autowired
    private ProduceBoxService produceBoxService;

    @PostMapping
    public ResponseEntity<ProduceBox> createProduceBox(@RequestBody ProduceBox produceBox) {
        ProduceBox createdProduceBox = produceBoxService.createProduceBox(produceBox);
        return new ResponseEntity<>(createdProduceBox, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProduceBox>> getAllProduceBoxes() {
        List<ProduceBox> boxes = produceBoxService.getAllProduceBoxes();
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduceBox> getProduceBoxById(@PathVariable Long id) {
        Optional<ProduceBox> box = produceBoxService.getProduceBoxById(id);
        return box.map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<ProduceBox>> getProduceBoxesByFarmId(@PathVariable Long farmId) {
        List<ProduceBox> boxes = produceBoxService.getProduceBoxesByFarmId(farmId);
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProduceBox>> getProduceBoxesByStatus(@PathVariable String status) {
        List<ProduceBox> boxes = produceBoxService.getProduceBoxesByStatus(status);
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduceBox> updateProduceBox(@PathVariable Long id, @RequestBody ProduceBox boxDetails) {
        try {
            ProduceBox updatedBox = produceBoxService.updateProduceBox(id, boxDetails);
            return new ResponseEntity<>(updatedBox, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduceBox(@PathVariable Long id) {
        produceBoxService.deleteProduceBox(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
