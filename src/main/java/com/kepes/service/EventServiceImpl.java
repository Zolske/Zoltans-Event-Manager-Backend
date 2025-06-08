package com.kepes.service;

import com.kepes.model.Event;
import com.kepes.model.Subscription;
import com.kepes.repository.EventRepository;
import com.kepes.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Service
public class EventServiceImpl implements EventService {

    //@Autowired
    //EventRepository eventRepository;
    private final EventRepository eventRepository;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    SubscriberServiceImpl subscriberServiceImpl;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, SubscriberRepository subscriberRepository) {
        this.eventRepository = eventRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Event createEvent(Event event) {
        event.setIdEvent(null);
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        eventRepository.findAll().forEach(eventList::add);
        return eventList;
    }

    /**
     * Get only the events to which the user has not already subscribed
     */
    @Override
    public List<Event> getNotSubscribedEvents(String userId){
        List<Event> eventList = getAllEvents();
        List<Event> subscribedEventList = subscriberServiceImpl.getSubscribedEvents(userId);
        for (Event subEvent : subscribedEventList) {
            eventList.removeIf(event -> event.getIdEvent() == subEvent.getIdEvent());
        }
        return eventList;
    }

    @Override
    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setTitle(updatedEvent.getTitle());
                    existingEvent.setDescShort(updatedEvent.getDescShort());
                    existingEvent.setDesc(updatedEvent.getDesc());
                    existingEvent.setDate(updatedEvent.getDate());
                    existingEvent.setTime(updatedEvent.getTime());
                    existingEvent.setAddress(updatedEvent.getAddress());
                    return eventRepository.save(existingEvent);
                })
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }

    @Override
    public void deleteEvent(Long eventId) {
        List<Subscription> existing = subscriberRepository.findByEventIdEvent(eventId);
        if (existing.isEmpty() == false){
            System.out.println("There are subscriptions.");
            String userId;
            for(Subscription subscription : existing){
               userId = subscription.getUser().getIdUser();
                System.out.println("user id: " + userId);
               subscriberServiceImpl.deleteSubscription(userId,eventId);
            }
        }
        eventRepository.deleteById(eventId);
    }
}
