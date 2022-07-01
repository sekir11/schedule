package com.schedule.application.payload;

import com.schedule.domain.model.Participant;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetParticipantPayload {

    private Integer eventDateId;

    private String name;

    private String status;

    public static List<GetParticipantPayload> of(List<Participant> participants) {
        List<GetParticipantPayload> result = new ArrayList<>();

        for (Participant participant : participants) {
            result.add(GetParticipantPayload.builder()
                    .name(participant.getUser().getName())
                    .eventDateId(participant.getEventDateId())
                    .status(participant.getStatus().getValue())
                    .build());
        }

        return result;
    }

    public static List<GetParticipantPayload> of(List<Participant> participants, String userName) {
        List<GetParticipantPayload> result = new ArrayList<>();

        participants.stream().filter(participant -> participant.getUser().getName().equals(userName))
                .forEach(participant -> result.add(GetParticipantPayload.builder()
                        .name(participant.getUser().getName())
                        .eventDateId(participant.getEventDateId())
                        .status(participant.getStatus().getValue())
                        .build()));

        for (Participant participant : participants) {
            if (!participant.getUser().getName().equals(userName)) {
                result.add(GetParticipantPayload.builder()
                        .name(participant.getUser().getName())
                        .eventDateId(participant.getEventDateId())
                        .status(participant.getStatus().getValue())
                        .build());
            }
        }

        return result;
    }


}
