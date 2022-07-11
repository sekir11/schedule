package com.schedule.application.session;

import com.schedule.domain.model.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * セッションで管理されるユーザー。
 */
@Data
@Builder
public class SessionUser implements Serializable {

  private static final long serialVersionUID = -2886094327617521428L;

  private String name;

  private String password;

  private String address;

  public static SessionUser of(User user) {
    return SessionUser.builder()
            .name(user.getName())
            .password(user.getPassword())
            .address(user.getAddress())
            .build();
  }
}
