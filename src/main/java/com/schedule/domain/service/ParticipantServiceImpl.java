package com.schedule.domain.service;

import com.schedule.domain.model.Participant;
import com.schedule.domain.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{

    private final ParticipantRepository participantRepository;

    @Override
    public void editParticipant(Participant participant) {
        participantRepository.editParticipant(participant);
    }
}
