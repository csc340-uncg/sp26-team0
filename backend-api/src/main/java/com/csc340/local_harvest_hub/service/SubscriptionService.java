package com.csc340.local_harvest_hub.service;

import com.csc340.local_harvest_hub.entity.Subscription;
import com.csc340.local_harvest_hub.entity.Subscription.SubscriptionStatus;
import com.csc340.local_harvest_hub.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> getSubscriptionsByCustomerId(Long customerId) {
        return subscriptionRepository.findByCustomerId(customerId);
    }

    public List<Subscription> getSubscriptionsByProduceBoxId(Long boxId) {
        return subscriptionRepository.findByProduceBoxId(boxId);
    }

    public List<Subscription> getSubscriptionsByStatus(SubscriptionStatus status) {
        return subscriptionRepository.findByStatus(status);
    }

    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscription.setCadence(subscriptionDetails.getCadence());
            subscription.setStatus(subscriptionDetails.getStatus());
            subscription.setEndDate(subscriptionDetails.getEndDate());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
