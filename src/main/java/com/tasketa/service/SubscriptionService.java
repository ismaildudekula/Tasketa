package com.tasketa.service;

import com.tasketa.model.PlanType;
import com.tasketa.model.Subscription;
import com.tasketa.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);

}
