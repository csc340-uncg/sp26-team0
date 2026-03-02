package com.csc340.local_harvest_hub.controller;

import com.csc340.local_harvest_hub.entity.UserModeration;
import com.csc340.local_harvest_hub.service.UserModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-moderations")
public class UserModerationController {
    
    @Autowired
    private UserModerationService userModerationService;
    
    @PostMapping
    public ResponseEntity<UserModeration> createUserModeration(@RequestBody UserModeration moderation) {
        UserModeration createdModeration = userModerationService.createUserModeration(moderation);
        return new ResponseEntity<>(createdModeration, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<UserModeration>> getAllUserModerations() {
        List<UserModeration> moderations = userModerationService.getAllUserModerations();
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserModeration> getUserModerationById(@PathVariable Long id) {
        Optional<UserModeration> moderation = userModerationService.getUserModerationById(id);
        return moderation.map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserModeration>> getModerationsByTargetUserId(@PathVariable Long userId) {
        List<UserModeration> moderations = userModerationService.getModerationsByTargetUserId(userId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }
    
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<UserModeration>> getModerationsByAdminId(@PathVariable Long adminId) {
        List<UserModeration> moderations = userModerationService.getModerationsByAdminId(adminId);
        return new ResponseEntity<>(moderations, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserModeration> updateUserModeration(@PathVariable Long id, @RequestBody UserModeration moderationDetails) {
        try {
            UserModeration updatedModeration = userModerationService.updateUserModeration(id, moderationDetails);
            return new ResponseEntity<>(updatedModeration, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserModeration(@PathVariable Long id) {
        userModerationService.deleteUserModeration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
