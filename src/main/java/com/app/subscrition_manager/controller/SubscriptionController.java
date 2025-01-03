package com.app.subscrition_manager.controller;

import com.app.subscrition_manager.entity.Subscription;
import com.app.subscrition_manager.exception.CustomException;
import com.app.subscrition_manager.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    //add subscription
    @PostMapping("/add")
    public ResponseEntity<?> addSubscription(@RequestBody Subscription subscription) {
        try {
            Subscription subscription1 = subscriptionService.addSubscription(subscription);
            return ResponseEntity.ok(subscription1);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //get subscription

    @GetMapping("/get")
    public ResponseEntity<?> getSubscription(@RequestParam String userId){
        return ResponseEntity.ok(subscriptionService.getSubscriptions(userId));
    }

    //update subscription
    @PostMapping("/update")
    public ResponseEntity<?> updateSubscription(@RequestBody Subscription subscription) {
        try{
            Subscription subscription1 = subscriptionService.updateSubscription(subscription);
            return ResponseEntity.ok(subscription1);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //remove subscription
    @DeleteMapping("/delete")
    public ResponseEntity<?> removeSubscription(@RequestParam String subscriptionId){
        try {
            Subscription subscription = subscriptionService.removeSubscription(subscriptionId);
            return ResponseEntity.ok(subscription);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
