package com.schedule.integration.entity;

import com.schedule.domain.model.Participant;
import com.schedule.domain.model.Status;
import com.schedule.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participants")
@Data
@Builder
@IdClass(ParticipantKey.class)
public class ParticipantEntity {

    @Id
    @Column(name = "event_date_id")
    private Integer eventDateId;

    @Id
    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "status", length = 1)
    private String status;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_name")
//    private UserEntity userEntity;


    /**
     * User ドメインモデルに変換します。
     *
     * @return User インスタンス
     */
    public Participant toModel() {
        return Participant.builder()
                .eventDateId(eventDateId)
//                .user(userEntity.toModel())
                .user(User.builder()
                          .name(name)
                          .build())
                .status(Status.of(status))
                .build();
    }


    public static ParticipantEntity toEntity(Participant participant) {
        return ParticipantEntity.builder()
                                .eventDateId(participant.getEventDateId())
                                .name(participant.getUser().getName())
                                .status(participant.getStatus().getValue())
                                .build();
    }
}
