package com.schedule.domain.repository;

import com.schedule.domain.model.EventDate;

import java.util.List;

public interface EventDateRepository {

    List<EventDate> getEventDateListByEventId(Integer eventId);

    List<EventDate> getEventDateListByEventIds(List<Integer> eventId);

    List<EventDate> createEventDateList(List<EventDate> eventDates);
}
