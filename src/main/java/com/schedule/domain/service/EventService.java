package com.schedule.domain.service;


import com.schedule.domain.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getEventsByName(String name);

    void createEvent(Event event);

    Event getEvent(Integer id);
}
