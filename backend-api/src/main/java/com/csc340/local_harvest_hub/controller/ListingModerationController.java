package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.ListingModeration;
import com.csc340.local_harvest_hub.service.ListingModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listing-moderations")
public class ListingModerationController {

    @Autowired
    private ListingModerationService listingModerationService;

    @PostMapping
    public ResponseEntity<ListingModeration> createListingModeration(@RequestBody ListingModeration moderation) {
        ListingModeration createdModeration = listingModerationService.createListingModeration(moderation);
        return new ResponseEntity<>(createdModeration, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListingModeration>> getAllListingModerations() {
        List<ListingModeration> moderations = listingModerationService.getAllListingModerations();
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingModeration> getListingModerationById(@PathVariable Long id) {
        Optional<ListingModeration> moderation = listingModerationService.getListingModerationById(id);
        return moderation.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/box/{boxId}")
    public ResponseEntity<List<ListingModeration>> getModerationsByProduceBoxId(@PathVariable Long boxId) {
        List<ListingModeration> moderations = listingModerationService.getModerationsByProduceBoxId(boxId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<ListingModeration>> getModerationsByAdminId(@PathVariable Long adminId) {
        List<ListingModeration> moderations = listingModerationService.getModerationsByAdminId(adminId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListingModeration> updateListingModeration(@PathVariable Long id, @RequestBody ListingModeration moderationDetails) {
        try {
            ListingModeration updatedModeration = listingModerationService.updateListingModeration(id, moderationDetails);
            return new ResponseEntity<>(updatedModeration, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListingModeration(@PathVariable Long id) {
        listingModerationService.deleteListingModeration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
