package com.schedule.integration.entity;

import com.schedule.domain.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
@Data
@Builder
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "event_name", length = 20)
    private String eventName;

    @Column(name = "memo", length = 200)
    private String memo;

    @Column(name = "create_user", length = 200)
    private String createUser;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private List<EventDateEntity> eventDateEntityList;


    public Event toModel() {
        return Event.builder()
                .id(id)
                .eventName(eventName)
                .memo(memo)
                .createUser(createUser)
                .eventDates(eventDateEntityList.stream().map(EventDateEntity::toModel).collect(Collectors.toList()))
                .build();
    }

    public static EventEntity toEntity(Event event) {
        return EventEntity.builder()
                          .eventName(event.getEventName())
                          .memo(event.getMemo())
                          .createUser(event.getCreateUser())
                          .build();
    }
}
