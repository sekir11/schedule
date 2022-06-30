package com.schedule.application.session;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * セッション管理するオブジェクトを表すクラスです。
 */
@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionContext implements Serializable {
  private static final long serialVersionUID = 247364541554012480L;
  private SessionUser sessionUser;
}
