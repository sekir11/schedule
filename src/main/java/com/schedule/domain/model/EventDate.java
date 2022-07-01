package com.schedule.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Data
@Builder
public class EventDate {

    private Integer id;

    private Integer eventId;

    private LocalDate candidateDate;

    private List<Participant> participants;

    public String displayDate() {
        return candidateDate.getMonthValue()
                + "/"
                + candidateDate.getDayOfMonth()
                + "("
                + candidateDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault())
                + ")";
    }

}
