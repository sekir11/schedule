package com.schedule.domain.model;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Participant {

    private Integer eventDateId;

    private User user;

    private Status status;
}
