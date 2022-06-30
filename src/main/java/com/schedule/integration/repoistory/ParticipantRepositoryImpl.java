package com.schedule.integration.repoistory;

import com.schedule.domain.model.Participant;
import com.schedule.domain.repository.ParticipantRepository;
import com.schedule.integration.entity.ParticipantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    @Autowired
    private final EntityManager entityManager;

    @Override
    public List<Participant> getParticipantListByEventDateId(Integer eventDateId) {
        return null;
    }

    @Override
    public List<Participant> createParticipant(List<Participant> participants) {
        List<ParticipantEntity> entities
                = participants.stream()
                              .map(ParticipantEntity::toEntity)
                              .collect(Collectors.toList());

        for (ParticipantEntity entity : entities) {
            entityManager.persist(entity);
        }

        return entities.stream().map(ParticipantEntity::toModel).collect(Collectors.toList());
    }
}
