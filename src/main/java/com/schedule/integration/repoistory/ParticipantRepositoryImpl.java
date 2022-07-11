package com.schedule.integration.repoistory;

import com.schedule.domain.model.Participant;
import com.schedule.domain.repository.ParticipantRepository;
import com.schedule.integration.entity.ParticipantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

    @Autowired
    private final EntityManager entityManager;

    private static final String UPDATE_CREATE_USER
            = "UPDATE ParticipantEntity SET name = :newName WHERE name = :oldName";

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


    @Override
    @Transactional
    public void editParticipant(Participant participant) {
        entityManager.merge(ParticipantEntity.toEntity(participant));
    }

    @Override
    @Transactional
    public void editParticipantUserName(String oldName, String newName) {
        entityManager.createQuery(UPDATE_CREATE_USER)
                .setParameter("oldName", oldName)
                .setParameter("newName", newName)
                .executeUpdate();
    }
}
