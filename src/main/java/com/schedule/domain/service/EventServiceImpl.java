package com.schedule.domain.service;


import com.schedule.domain.model.Event;
import com.schedule.domain.model.EventDate;
import com.schedule.domain.model.Participant;
import com.schedule.domain.repository.EventDateRepository;
import com.schedule.domain.repository.EventRepository;
import com.schedule.domain.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventDateRepository eventDateRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public List<Event> getEventsByName(String name) {
        return eventRepository.getEventByParticipantName(name);
    }


    @Override
    @Transactional
    public void createEvent(Event event) {
        Integer eventId = eventRepository.createEvent(event);
        event.getEventDates().forEach(eventDate -> eventDate.setEventId(eventId));
        List<EventDate> eventDates
                = eventDateRepository.createEventDateList(event.getEventDates());

        List<Participant> participants = new ArrayList<>();
        for (EventDate eventDate : event.getEventDates()) {
            for (EventDate registeredEventDate : eventDates) {
                if (eventDate.getCandidateDate().equals(registeredEventDate.getCandidateDate())) {
                    eventDate.getParticipants().forEach(participant -> participant.setEventDateId(registeredEventDate.getId()));
                    participants.addAll(eventDate.getParticipants());
                    break;
                }
            }
        }
        participantRepository.createParticipant(participants);
    }

    @Override
    public Event getEvent(Integer id) {
        return eventRepository.getEvent(id);
    }
}
