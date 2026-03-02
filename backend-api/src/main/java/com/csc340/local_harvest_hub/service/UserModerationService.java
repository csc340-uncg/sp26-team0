package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.UserModeration;
import com.csc340.local_harvest_hub.repository.UserModerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserModerationService {
    
    @Autowired
    private UserModerationRepository userModerationRepository;
    
    public UserModeration createUserModeration(UserModeration moderation) {
        return userModerationRepository.save(moderation);
    }
    
    public Optional<UserModeration> getUserModerationById(Long id) {
        return userModerationRepository.findById(id);
    }
    
    public List<UserModeration> getAllUserModerations() {
        return userModerationRepository.findAll();
    }
    
    public List<UserModeration> getModerationsByTargetUserId(Long userId) {
        return userModerationRepository.findByTargetUserId(userId);
    }
    
    public List<UserModeration> getModerationsByAdminId(Long adminId) {
        return userModerationRepository.findByAdminId(adminId);
    }
    
    public UserModeration updateUserModeration(Long id, UserModeration moderationDetails) {
        return userModerationRepository.findById(id).map(moderation -> {
            moderation.setAction(moderationDetails.getAction());
            moderation.setReason(moderationDetails.getReason());
            return userModerationRepository.save(moderation);
        }).orElseThrow(() -> new RuntimeException("UserModeration not found"));
    }
    
    public void deleteUserModeration(Long id) {
        userModerationRepository.deleteById(id);
    }
}
