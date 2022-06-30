package com.schedule.integration.entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ParticipantKey implements Serializable {

    @Column(name = "event_date_id")
    private Integer eventDateId;

    @Column(name = "name", length = 20)
    private String name;
}
