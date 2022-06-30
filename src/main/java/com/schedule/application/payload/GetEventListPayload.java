package com.schedule.application.payload;

import com.schedule.domain.model.Event;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class GetEventListPayload {

    private List<GetEventPayload> events;

    public static GetEventListPayload of(List<Event> eventList) {
        return GetEventListPayload.builder()
                .events(eventList.stream().map(GetEventPayload::of).collect(Collectors.toList()))
                .build();
    }
}
