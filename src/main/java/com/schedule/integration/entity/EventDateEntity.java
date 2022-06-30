package com.schedule.integration.entity;

import com.schedule.domain.model.EventDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_dates")
@Data
@Builder
public class EventDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "candidate_date")
    private LocalDate candidateDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_date_id")
    private List<ParticipantEntity> participantEntities;

    public EventDate toModel() {
        return EventDate.builder()
                .id(id)
                .eventId(eventId)
                .candidateDate(candidateDate)
                .build();
    }

    public static EventDateEntity toEntity(EventDate eventDate) {
        return EventDateEntity.builder()
                              .eventId(eventDate.getEventId())
                              .candidateDate(eventDate.getCandidateDate())
                              .build();
    }
}
