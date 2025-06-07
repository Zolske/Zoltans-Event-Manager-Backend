package com.kepes.controller;

import com.kepes.dto.CreateSubscriptionRequest;
import com.kepes.helper.GetHeader;
import com.kepes.model.Event;
import com.kepes.model.Subscription;
import com.kepes.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    // create a new entry in the joined table with user and the subscribed event
    @PostMapping("/subscribe")
    public ResponseEntity<Subscription> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        Subscription subscriber = subscriberService.createSubscription(request.getUserId(), request.getEventId());
        return new ResponseEntity<>(subscriber, GetHeader.success("Successful subscribed to Event."), HttpStatus.OK);
    }

    // get all the events to which the user has subscribed
    @GetMapping("/subscribedEvents/{userId}")
    public ResponseEntity<List<Event>> getSubscribedEvents(@PathVariable String userId) {
        List<Event> events = subscriberService.getSubscribedEvents(userId);
        return ResponseEntity.ok(events);
    }

//    @DeleteMapping("/delete{userId}")
//    public ResponseEntity<Void> deleteSubscription(@PathVariable String userId) {
//        subscriberService.deleteSubscription(userId);
//        return ResponseEntity.ok().build();
//    }
}
