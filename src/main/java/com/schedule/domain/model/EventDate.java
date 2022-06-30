package com.schedule.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EventDate {

    private Integer id;

    private Integer eventId;

    private LocalDate candidateDate;

    private List<Participant> participants;
}
