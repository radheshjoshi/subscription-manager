package com.app.subscrition_manager.service.impl;

import com.app.subscrition_manager.entity.Subscription;
import com.app.subscrition_manager.exception.CustomException;
import com.app.subscrition_manager.repository.SubscriptionRepository;
import com.app.subscrition_manager.service.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Subscription addSubscription(Subscription subscription) throws CustomException {
        validateSubscription(subscription);
        repository.save(subscription);
        return subscription;
    }

    public List<Subscription> getSubscriptions(String userId) {
        return repository.findAllByUserId(userId);
    }

    @Transactional
    public Subscription updateSubscription(Subscription subscription) throws Exception {
        validateSubscription(subscription);
        Subscription tempSubscription = repository.findById(subscription.getId())
                .orElseThrow(() -> new CustomException("Subscription Not found."));
        tempSubscription.setCost(subscription.getCost());
        tempSubscription.setRenewalDate(subscription.getRenewalDate());
        repository.save(tempSubscription);
        return tempSubscription;
    }

    @Transactional
    public Subscription removeSubscription(String subscriptionId) throws Exception {
        Subscription subscription = repository.findById(subscriptionId)
                .orElseThrow(() -> new CustomException("Subscription Not Found"));
        repository.deleteById(subscriptionId);
        return subscription;
    }

    private void validateSubscription(Subscription subscription) {
        if (subscription.getPlatformName() == null || subscription.getPlatformName().isEmpty()) {
            throw new CustomException("Platform name cannot be empty.");
        }
        if (subscription.getCost() == null || subscription.getCost() <= 0.0) {
            throw new CustomException("Cost for a subscription must be greater than zero.");
        }
        if (subscription.getRenewalDate() == null) {
            throw new CustomException("Renewal date is required.");
        }
    }
}
