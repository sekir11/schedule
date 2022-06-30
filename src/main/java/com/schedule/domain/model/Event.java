package com.schedule.domain.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Event {

    private Integer id;

    private String eventName;

    private String memo;

    private String createUser;

    private List<EventDate> eventDates;
}
