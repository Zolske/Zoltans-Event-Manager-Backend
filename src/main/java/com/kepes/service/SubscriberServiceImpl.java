package com.kepes.service;

import com.kepes.exception.ItemNotFoundException;
import com.kepes.model.Event;
import com.kepes.model.Subscription;
import com.kepes.model.User;
import com.kepes.repository.EventRepository;
import com.kepes.repository.SubscriberRepository;
import com.kepes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository,
                                 UserRepository userRepository,
                                 EventRepository eventRepository) {
        this.subscriberRepository = subscriberRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Subscription createSubscription(String userId, Long eventId) {

        Optional<Subscription> existing = subscriberRepository.findByUserIdUserAndEventIdEvent(userId, eventId);
        if (existing.isPresent())
            throw new ItemNotFoundException("User already subscribed to event.");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ItemNotFoundException("Event not found"));

        Subscription subscriber = new Subscription();
        subscriber.setUser(user);
        subscriber.setEvent(event);

        return subscriberRepository.save(subscriber);
    }

    @Override
    public void deleteSubscription(String userId, Long eventId) {

        Optional<Subscription> existing = subscriberRepository.findByUserIdUserAndEventIdEvent(userId, eventId);
        existing.ifPresent(subscriberRepository::delete);
        if (existing.isEmpty())
            throw new ItemNotFoundException("Subscription not found");
    }

//    @Override
//    public Subscriber saveSubscriber(Subscriber subscriber) {
//        return subscriberRepository.save(subscriber);
//    }
//
//    @Override
//    public List<Subscriber> getAllSubscribers() {
////        List<Event> subscriberList = new ArrayList<>();
////        subscriberRepository.findAll().forEach(subscriberList::add);
////        return subscriberList;
//        return (List<Subscriber>) subscriberRepository.findAll();
//    }
//
//    @Override
//    public Subscriber getSubscriberById(Long id) {
//        Optional<Subscriber> result = subscriberRepository.findById(id);
//        return result.orElse(null);
//    }
//
//    @Override
//    public void deleteSubscription(Long subscriptionId) {
//        subscriberRepository.deleteById(subscriptionId);
//    }

    @Override
    public List<Event> getSubscribedEvents(String userId) {
        List<Subscription> subscriptions = subscriberRepository.findByUserIdUser(userId);
        return subscriptions.stream()
                .map(Subscription::getEvent)
                .collect(Collectors.toList());
    }
}
