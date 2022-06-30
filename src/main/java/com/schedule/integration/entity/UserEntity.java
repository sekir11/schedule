package com.schedule.integration.entity;

import com.schedule.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
public class UserEntity {

    @Id
    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "password", length = 15)
    private String password;

    @Column(name = "address", length = 15)
    private String address;


    /**
     * User ドメインモデルに変換します。
     *
     * @return User インスタンス
     */
    public User toModel() {
        return User.builder()
                .name(userName)
                .password(password)
                .address(address)
                .build();
    }

    public static UserEntity toEntity(String name, String password, String address) {
        return UserEntity.builder()
                         .userName(name)
                         .password(password)
                         .address(address)
                         .build();
    }
}
