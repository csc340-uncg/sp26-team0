package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.ListingModeration;
import com.csc340.local_harvest_hub.repository.ListingModerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingModerationService {

    @Autowired
    private ListingModerationRepository listingModerationRepository;

    public ListingModeration createListingModeration(ListingModeration moderation) {
        return listingModerationRepository.save(moderation);
    }

    public Optional<ListingModeration> getListingModerationById(Long id) {
        return listingModerationRepository.findById(id);
    }

    public List<ListingModeration> getAllListingModerations() {
        return listingModerationRepository.findAll();
    }

    public List<ListingModeration> getModerationsByProduceBoxId(Long boxId) {
        return listingModerationRepository.findByProduceBoxId(boxId);
    }

    public List<ListingModeration> getModerationsByAdminId(Long adminId) {
        return listingModerationRepository.findByAdminId(adminId);
    }

    public ListingModeration updateListingModeration(Long id, ListingModeration moderationDetails) {
        return listingModerationRepository.findById(id).map(moderation -> {
            moderation.setAction(moderationDetails.getAction());
            moderation.setReason(moderationDetails.getReason());
            return listingModerationRepository.save(moderation);
        }).orElseThrow(() -> new RuntimeException("ListingModeration not found"));
    }

    public void deleteListingModeration(Long id) {
        listingModerationRepository.deleteById(id);
    }
}
