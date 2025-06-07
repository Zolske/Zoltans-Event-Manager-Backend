package com.kepes.service;

import com.kepes.model.Subscription;
import com.kepes.model.Event;

import java.util.List;

public interface SubscriberService {
    Subscription createSubscription(String userId, Long eventId);
//    Subscriber saveSubscriber(Subscriber subscriber);
//    List<Subscriber> getAllSubscribers();
//    Subscriber getSubscriberById(Long id);
    void deleteSubscription(String userId, Long eventId);

    List<Event> getSubscribedEvents(String userId);
}
