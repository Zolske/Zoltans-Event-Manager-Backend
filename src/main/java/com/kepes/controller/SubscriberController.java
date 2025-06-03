package com.kepes.controller;

import com.kepes.dto.CreateSubscriptionRequest;
import com.kepes.model.Event;
import com.kepes.model.Subscription;
import com.kepes.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriberController {


    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        Subscription subscriber = subscriberService.createSubscription(request.getUserId(), request.getEventId());
        return ResponseEntity.ok(subscriber);
    }

    @GetMapping("/subscribedEvents/{userId}")
    public ResponseEntity<List<Event>> getSubscribedEvents(@PathVariable String userId) {
        List<Event> events = subscriberService.getSubscribedEvents(userId);
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/delete{subscriptionId}")
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        subscriberService.deleteSubscription(subscriptionId);
    }
}
