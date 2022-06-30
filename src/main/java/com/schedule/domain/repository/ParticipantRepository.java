package com.schedule.domain.repository;

import com.schedule.domain.model.Participant;

import java.util.List;

public interface ParticipantRepository {

    List<Participant> getParticipantListByEventDateId(Integer eventDateId);

    List<Participant> createParticipant(List<Participant> participants);
}
