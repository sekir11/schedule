package com.schedule.integration.repoistory;

import com.schedule.domain.model.Event;
import com.schedule.domain.repository.EventRepository;
import com.schedule.integration.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private final EntityManager entityManager;

    private final String SELECT_EVENT_BY_NAME
            = "SELECT e FROM EventEntity AS e" +
            " LEFT JOIN EventDateEntity AS ed ON e.id = ed.eventId" +
            " LEFT JOIN ParticipantEntity AS p ON ed.id = p.eventDateId" +
//            " LEFT JOIN UserEntity AS u ON u.userName = p.name" +
            " WHERE e.createUser = :createUser";

    private final String SELECT_EVENT_BY_PARTICIPANT_NAME
            = "SELECT e FROM EventEntity AS e" +
            " WHERE EXISTS(SELECT ed FROM EventDateEntity AS ed" +
            " LEFT JOIN ParticipantEntity AS p ON ed.id = p.eventDateId" +
            " WHERE p.name = :name AND e.id = ed.eventId)";

    private final String SELECT_EVENT_BY_ID
            = "SELECT e FROM EventEntity AS e" +
            " WHERE EXISTS(SELECT ed FROM EventDateEntity AS ed" +
            " LEFT JOIN ParticipantEntity AS p ON ed.id = p.eventDateId" +
            " WHERE e.id = :id AND e.id = ed.eventId)";

    private final String UPDATE_CREATE_USER
            = "UPDATE EventEntity SET create_user = :newName WHERE create_user = :oldName";

    @Override
    public List<Event> getEventByUserName(String userName) {
        TypedQuery<EventEntity> query = entityManager.createQuery(SELECT_EVENT_BY_NAME, EventEntity.class);
        query.setParameter("createUser", userName);

        List<EventEntity> eventEntities = query.getResultList();

        return eventEntities.stream().map(EventEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventByParticipantName(String name) {
        TypedQuery<EventEntity> query = entityManager.createQuery(SELECT_EVENT_BY_PARTICIPANT_NAME, EventEntity.class);
        query.setParameter("name", name);

        List<EventEntity> eventEntities = query.getResultList();

        return eventEntities.stream().map(EventEntity::toModel).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Integer createEvent(Event event) {
        EventEntity entity = EventEntity.toEntity(event);
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    public Event getEvent(Integer id) {
        TypedQuery<EventEntity> query = entityManager.createQuery(SELECT_EVENT_BY_ID, EventEntity.class);
        query.setParameter("id", id);

        EventEntity eventEntity = query.getSingleResult();

        return eventEntity.toModel();
    }

    @Override
    @Transactional
    public void editCreateUser(String oldName, String newName) {
        entityManager.createQuery(UPDATE_CREATE_USER)
                .setParameter("oldName", oldName)
                .setParameter("newName", newName)
                .executeUpdate();
    }
}
