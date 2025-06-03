package com.kepes.service;

import com.kepes.model.Event;
import com.kepes.model.Subscription;
import com.kepes.model.User;
import com.kepes.repository.EventRepository;
import com.kepes.repository.SubscriberRepository;
import com.kepes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Subscription subscriber = new Subscription();
        subscriber.setUser(user);
        subscriber.setEvent(event);

        return subscriberRepository.save(subscriber);
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
    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriberRepository.deleteById(subscriptionId);
    }

    @Override
    public List<Event> getSubscribedEvents(String userId){
        List<Subscription> subscriptions = subscriberRepository.findByUserIdUser(userId);
        return subscriptions.stream()
                .map(Subscription::getEvent)
                .collect(Collectors.toList());
    }
}
