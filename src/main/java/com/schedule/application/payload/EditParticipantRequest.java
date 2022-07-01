package com.schedule.application.payload;

import com.schedule.domain.model.Participant;
import com.schedule.domain.model.Status;
import com.schedule.domain.model.User;
import lombok.Data;

@Data
public class EditParticipantRequest {

    private Integer eventDateId;

    private String userName;

    private String status;

    public Participant toModel() {
        return Participant.builder()
                .eventDateId(eventDateId)
                .user(User.builder()
                        .name(userName)
                        .build())
                .status(Status.of(status))
                .build();
    }
}
