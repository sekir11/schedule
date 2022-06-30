package com.schedule.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * メッセージのレベル。
 */
@AllArgsConstructor
@Getter
public enum Level {

  TRACE("TRACE"),
  DEBUG("DEBUG"),
  INFO("INFO"),
  WARN("WARN"),
  ERROR("ERROR"),
  FATAL("FATAL");

  private String value;

}
