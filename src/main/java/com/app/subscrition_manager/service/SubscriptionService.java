package com.app.subscrition_manager.service;

import com.app.subscrition_manager.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    //add subscription business logic
    public Subscription addSubscription(Subscription subscription) throws Exception;

    //get subscription business logic
    public List<Subscription> getSubscriptions(String userId);

    //update subscription
    public Subscription updateSubscription(Subscription subscription) throws Exception;

    //remove subscription business logic
    public Subscription removeSubscription(String subscriptionId) throws Exception;
}
