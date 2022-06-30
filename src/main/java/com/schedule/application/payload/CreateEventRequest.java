package com.schedule.application.payload;

import com.schedule.domain.model.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateEventRequest {

    private String eventName;

    private String memo;

    private List<LocalDate> candidateDates;

    private List<CreateEventUserRequest> users;

    private String createUserName;

    public Event toModel() {
        return Event.builder()
                    .eventName(eventName)
                    .memo(memo)
                    .createUser(createUserName)
                    .eventDates(eventDateOf())
                    .build();
    }

    private List<EventDate> eventDateOf() {
        List<EventDate> result = new ArrayList<>();
        for (LocalDate date : candidateDates) {
            result.add(EventDate.builder()
                    .candidateDate(date)
                    .participants(participantOf())
                    .build());
        }
        return result;
    }

    private List<Participant> participantOf() {
        List<Participant> result = new ArrayList<>();
        for (CreateEventUserRequest user: users) {
            result.add(Participant.builder()
                                 .user(User.builder()
                                           .name(user.getName())
                                           .address(user.getAddress())
                                           .build())
                                 .status(Status.NONE)
                                 .build());
        }
        return result;
    }


}
