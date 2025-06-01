package com.kepes.service;


import com.kepes.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);
    Optional<Event> getEventById(Long id);
    List<Event> getAllEvents();
    //Event updateEvent(Long id, Event updatedEvent);
    void deleteEvent(Long id);
}
