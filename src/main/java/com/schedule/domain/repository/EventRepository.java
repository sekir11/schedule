package com.schedule.domain.repository;

import com.schedule.domain.model.Event;

import java.util.List;

public interface EventRepository {

    List<Event> getEventByUserName(String userName);

    List<Event> getEventByParticipantName(String name);

    Integer createEvent(Event event);

    Event getEvent(Integer id);
}
