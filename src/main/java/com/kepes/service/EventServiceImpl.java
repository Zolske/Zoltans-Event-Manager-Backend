package com.kepes.service;

import com.kepes.model.Event;
import com.kepes.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    //@Autowired
    //EventRepository eventRepository;
    private final EventRepository eventRepository;

    @Autowired
    SubscriberServiceImpl subscriberServiceImpl;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
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
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
