package com.schedule.application.payload;

import com.schedule.domain.model.Event;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetEventPayload {

    private Integer id;

    private String eventName;

    private String memo;

    private List<GetEventDatePayload> eventDates;

    public static GetEventPayload of(Event event) {
        return GetEventPayload.builder()
                              .id(event.getId())
                              .eventName(event.getEventName())
                              .memo(event.getMemo())
                              .eventDates(GetEventDatePayload.of(event.getEventDates()))
                              .build();
    }

    public static GetEventPayload of(Event event, String userName) {
        return GetEventPayload.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .memo(event.getMemo())
                .eventDates(GetEventDatePayload.of(event.getEventDates(), userName))
                .build();
    }
}
