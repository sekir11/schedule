package com.schedule.application.payload;

import com.schedule.domain.model.Event;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetEventPayload {

    private Integer id;

    private String eventName;

    private String memo;

    public static GetEventPayload of(Event event) {
        return GetEventPayload.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .memo(event.getMemo())
                .build();
    }
}
