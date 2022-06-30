package com.schedule.integration.repoistory;

import com.schedule.domain.model.EventDate;
import com.schedule.domain.repository.EventDateRepository;
import com.schedule.integration.entity.EventDateEntity;
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
public class EventDateRepositoryImpl implements EventDateRepository {

    @Autowired
    private final EntityManager entityManager;

    private final String SELECT_EVENT_DATE_BY_EVENT_ID
            = "FROM EventDateEntity WHERE event_id = :eventId";

    private final String SELECT_EVENT_DATE_BY_EVENT_IDS
            = "FROM EventDateEntity WHERE event_id IN (:eventIds)";

    @Override
    public List<EventDate> getEventDateListByEventId(Integer eventId) {
        TypedQuery<EventDateEntity> query = entityManager.createQuery(SELECT_EVENT_DATE_BY_EVENT_ID, EventDateEntity.class);
        query.setParameter("eventId", eventId);

        List<EventDateEntity> eventEntities = query.getResultList();

        return eventEntities.stream().map(EventDateEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public List<EventDate> getEventDateListByEventIds(List<Integer> eventIds) {
        TypedQuery<EventDateEntity> query = entityManager.createQuery(SELECT_EVENT_DATE_BY_EVENT_IDS, EventDateEntity.class);
        query.setParameter("eventIds", eventIds);

        List<EventDateEntity> eventEntities = query.getResultList();

        return eventEntities.stream().map(EventDateEntity::toModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<EventDate> createEventDateList(List<EventDate> eventDates) {
        List<EventDateEntity> entities =
                eventDates.stream().map(EventDateEntity::toEntity).collect(Collectors.toList());

        for (EventDateEntity entity : entities) {
            entityManager.persist(entity);
        }

        return entities.stream().map(EventDateEntity::toModel).collect(Collectors.toList());
    }
}
