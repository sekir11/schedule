package com.schedule.application.payload;

import com.schedule.domain.model.EventDate;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetEventDatePayload {

    private Integer id;

    private String candidateDate;

    private List<GetParticipantPayload> participants;

    public static List<GetEventDatePayload> of(List<EventDate> eventDates) {
        List<GetEventDatePayload> result = new ArrayList<>();

        for (EventDate eventDate : eventDates) {
            result.add(GetEventDatePayload.builder()
                    .id(eventDate.getId())
                    .candidateDate(eventDate.displayDate())
                    .participants(GetParticipantPayload.of(eventDate.getParticipants()))
                    .build());
        }

        return result;
    }

    public static List<GetEventDatePayload> of(List<EventDate> eventDates, String userName) {
        List<GetEventDatePayload> result = new ArrayList<>();

        for (EventDate eventDate : eventDates) {
            result.add(GetEventDatePayload.builder()
                    .id(eventDate.getId())
                    .candidateDate(eventDate.displayDate())
                    .participants(GetParticipantPayload.of(eventDate.getParticipants(), userName))
                    .build());
        }

        return result;
    }
}
