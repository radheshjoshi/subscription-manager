package com.app.subscrition_manager.repository;

import com.app.subscrition_manager.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    @Query(value = "SELECT * FROM subscriptions WHERE user_id = :userId", nativeQuery = true)
    List<Subscription> findAllByUserId(String userId);
}
