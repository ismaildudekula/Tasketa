package com.tasketa.controller;


import com.tasketa.model.PlanType;
import com.tasketa.model.Subscription;
import com.tasketa.model.User;
import com.tasketa.service.SubscriptionService;
import com.tasketa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription usersSubscription = subscriptionService.getUsersSubscription(user.getId());
        return new ResponseEntity<>(usersSubscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade_subscription")
    public ResponseEntity<Subscription> upgradeSubscription(
            @RequestBody PlanType planType,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

}
