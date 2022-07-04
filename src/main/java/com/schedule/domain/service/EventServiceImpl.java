package com.schedule.domain.service;


import com.schedule.domain.component.MailComponent;
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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventDateRepository eventDateRepository;
    private final ParticipantRepository participantRepository;
    private final MailComponent mailComponent;

    @Override
    public List<Event> getEventsByName(String name) {
        return eventRepository.getEventByParticipantName(name);
    }


    @Override
    @Transactional
    public void createEvent(Event event) {
        Integer eventId = eventRepository.createEvent(event);
        event.getEventDates().forEach(eventDate -> eventDate.setEventId(eventId));
        event.setId(eventId);
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
        List<String> sendNameList = new ArrayList<>(new HashSet<>(participants.stream().map(participant -> participant.getUser().getName()).collect(Collectors.toList())));
        sendNameList.forEach(name -> mailComponent.sendMail(event, name));
    }

    @Override
    public Event getEvent(Integer id) {
        return eventRepository.getEvent(id);
    }
}
